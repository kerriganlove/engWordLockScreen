package com.example.engwordlockscreen.presentation.main.setting.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.engwordlockscreen.databinding.FragmentSettingBinding
import com.example.engwordlockscreen.presentation.main.WordViewModel
import com.example.engwordlockscreen.presentation.main.word.WordEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    /*
     * Variables
     */

    private lateinit var binding : FragmentSettingBinding
    private val viewModel by activityViewModels<WordViewModel>()

    /*
     * LifeCycle Callback
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonClick()
    }

    /*
     * Event
     */

    private fun buttonClick() {
        binding.clearDbAll.setOnClickListener {
            clearDB()
        }
    }
    private fun clearDB() {
        viewModel.onEvent(WordEvent.DeleteAll)
    }
}