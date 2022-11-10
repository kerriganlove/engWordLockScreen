package com.example.engwordlockscreen.presentation.quiz.components

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.FragmentMuitichoiceBinding
import com.example.engwordlockscreen.presentation.quiz.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MultiChoiceFragment : Fragment() {

    private var _binding : FragmentMuitichoiceBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<QuizViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        _binding = FragmentMuitichoiceBinding.inflate(layoutInflater,container, false)
        initData()
        return binding.root
    }

    /*
     * Initialize Anything
     */

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            Log.d("Data Transfer", viewModel.getMultiChoiceList().first().toList().toString())
        }
    }
}