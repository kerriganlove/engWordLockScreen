package com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.FragmentMuitichoiceBinding
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.presentation.lockscreen.quiz.QuizViewModel
import com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice.recyclerview.MultiChoiceRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

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
            viewModel.getMultiChoiceList().collect {
                Log.d("TRANSFER",it.toString())
                if ( it.isEmpty() || it.size < 3) {
                    setEmptyView(it.size)
                }
                else {
                    setGridView(it)
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
        binding.answerListGridview.visibility = View.GONE
        binding.correctCount.visibility = View.GONE
        binding.emptySavedWordsBtn.setOnClickListener { requireActivity().finish() }
    }

    private fun setGridView(list : List<WordEntities>) {
        recyclerViewAdapter = MultiChoiceRecyclerViewAdapter()
        recyclerViewAdapter.setList(list)
        binding.answerListGridview.apply {
            adapter = recyclerViewAdapter
            layoutManager = GridLayoutManager(context,3)
        }
        val correctNum = Random.nextInt(list.size)
        val remainCount = list.groupBy { it.word == list[correctNum].word }.values.size
        binding.correctWords.text = list[correctNum].word
        binding.emptySavedWordsBtn.visibility = View.GONE
        binding.correctCount.text = remainCount.toString()
    }
}