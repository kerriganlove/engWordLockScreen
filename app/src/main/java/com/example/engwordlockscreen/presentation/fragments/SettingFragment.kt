package com.example.engwordlockscreen.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.engwordlockscreen.databinding.FragmentSettingBinding
import com.example.engwordlockscreen.presentation.word.WordEvent
import com.example.engwordlockscreen.presentation.word.WordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private lateinit var binding : FragmentSettingBinding
    private val viewModel by viewModels<WordViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        buttonClick()
        return binding.root
    }

    private fun buttonClick()
    {
        binding.clearDbAll.setOnClickListener {
            clearDB()
        }
    }
    private fun clearDB()
    {
        viewModel.onEvent(WordEvent.DeleteAll)
    }
}