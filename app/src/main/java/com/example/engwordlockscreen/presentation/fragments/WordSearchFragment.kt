package com.example.engwordlockscreen.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.databinding.FragmentWordSearchBinding
import com.example.engwordlockscreen.presentation.main.WordViewModel
import com.example.engwordlockscreen.presentation.main.word.components.wordlist.recyclerview.WordListRecyclerViewAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WordSearchFragment : Fragment() {

    private var _binding : FragmentWordSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<WordViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWordSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchListView.apply {
            adapter = WordListRecyclerViewAdapter()
            layoutManager = LinearLayoutManager(context)
        }
        initView()
    }

    private fun initView() {

    }
}