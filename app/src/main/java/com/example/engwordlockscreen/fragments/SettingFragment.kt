package com.example.engwordlockscreen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.engwordlockscreen.data.datasource.WordDatabase
import com.example.engwordlockscreen.databinding.FragmentSettingBinding
import com.example.engwordlockscreen.presentation.word.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        viewModel.deleteAllList()
    }
}