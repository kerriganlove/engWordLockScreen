package com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.constants.CustomConst
import com.example.engwordlockscreen.presentation.utils.dialogs.DialogTag
import com.example.engwordlockscreen.constants.UiState
import com.example.engwordlockscreen.databinding.FragmentMuitichoiceBinding
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.presentation.lockscreen.quiz.QuizViewModel
import com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice.recyclerview.MultiChoiceItemDecoration
import com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice.recyclerview.MultiChoiceRecyclerViewAdapter
import com.example.engwordlockscreen.presentation.utils.dialogs.DialogUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MultiChoiceFragment : Fragment() {

    private var _binding : FragmentMuitichoiceBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<QuizViewModel>()
    private lateinit var recyclerViewAdapter : MultiChoiceRecyclerViewAdapter

    /*
     * LifeCycle Callback
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMuitichoiceBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    /*
     * Initialize Data
     */

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.getMultiChoiceList().collect { uiState ->
                    when(uiState) {
                        is UiState.Fail -> {
                            DialogUtil.dismissDialog(DialogTag.LoadingDialog)
                            setEmptyView(uiState.data as Int)
                        }
                        is UiState.Success ->{
                            DialogUtil.dismissDialog(DialogTag.LoadingDialog)
                            setCorrectView(uiState.data as List<WordEntities>)
                        }
                        is UiState.Loading -> {
                            DialogUtil.showDialog(DialogTag.LoadingDialog, parentFragmentManager)
                        }
                        is UiState.RefreshData -> {
                            TODO()
                        }
                    }
                }
            }
        }
    }

    /*
     * Setting Views
     */

    private fun setEmptyView(size : Int) {
        val string = if (size != 0) context?.getString(R.string.not_enough_quiz_data_text) else context?.getString(R.string.not_available_saved_word)
        binding.correctWords.text = string
        binding.answerListGridview.visibility = View.INVISIBLE
        binding.correctCount.visibility = View.GONE
        binding.emptySavedWordsBtn.apply {
            visibility = View.VISIBLE
            setOnClickListener { requireActivity().finish() }
        }
    }

    private fun setCorrectView(list : List<WordEntities>) {
        recyclerViewAdapter = MultiChoiceRecyclerViewAdapter(onClick = {s : Any -> judge(s as WordEntities) })
        recyclerViewAdapter.setList(list)
        binding.answerListGridview.apply {
            adapter = recyclerViewAdapter
            layoutManager = GridLayoutManager(context,3)
            addItemDecoration(MultiChoiceItemDecoration())
        }
        viewLifecycleOwner.lifecycleScope.launch{
            viewModel.correctList.collect {
                when(it.size) {
                    0 -> {
                        showCorrectAnimate()
                    }
                    else -> {
                        binding.correctCount.text = it.size.toString()
                        binding.correctWords.text = it[0].word
                    }
                }
            }
        }
    }

    private fun judge(word : WordEntities) : Boolean {
        var answer = false
        viewLifecycleOwner.lifecycleScope.launch {
            answer = viewModel.judgeWordByList(word)
        }
        return answer
    }

    private suspend fun showCorrectAnimate() {
        DialogUtil.showDialog(tag = DialogTag.CorrectAnswerDialog, fm = parentFragmentManager)
        delay(CustomConst.ANIMATE_CORRECT_TIME)
        DialogUtil.dismissDialog(DialogTag.CorrectAnswerDialog)
    }
}