package com.example.engwordlockscreen.presentation.lockscreen.quiz.components.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.engwordlockscreen.constants.UiState
import com.example.engwordlockscreen.databinding.FragmentPuzzleBinding
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.presentation.lockscreen.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PuzzleFragment : Fragment() {

    private var _binding : FragmentPuzzleBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<QuizViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPuzzleBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPuzzleList().collect { uiState ->
                when(uiState) {
                    is UiState.Fail -> {
                        setEmptyData(uiState.err_data as Int)
                    }
                    is UiState.Success -> {
                        setCorrectView(uiState.suc_data as List<WordEntities>)
                    }
                }
            }
        }
    }

    private fun setEmptyData(size : Int) {

    }

    private fun setCorrectView(List : List<WordEntities>) {

    }

    private fun judge(word : WordEntities) {
        when (viewModel.judgeWord(word)){
            true -> {
                showCorrectAnimate()
            }
            false -> {
                showIncorrectAnimate()
            }
        }
    }

    private fun showCorrectAnimate() {

    }
    private fun showIncorrectAnimate() {

    }
}