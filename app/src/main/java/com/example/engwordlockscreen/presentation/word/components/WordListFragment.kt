package com.example.engwordlockscreen.presentation.word.components

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.presentation.utils.recyclerview.adapters.WordListRecyclerViewAdapter
import com.example.engwordlockscreen.databinding.FragmentWordListBinding
import com.example.engwordlockscreen.presentation.utils.dialogs.CustomDialog
import com.example.engwordlockscreen.presentation.word.WordEvent
import com.example.engwordlockscreen.presentation.word.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext

@AndroidEntryPoint
class WordListFragment : Fragment() {

    /*
     * Values
     */
    private var _binding : FragmentWordListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<WordViewModel>()
    private lateinit var adapter : WordListRecyclerViewAdapter
    private val dlgUtil by lazy { CustomDialog(requireContext())}

    /*
     * LifeCycle Callback
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordListBinding.inflate(inflater,container,false)
        initView()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
     * Functions
     * Functions For Initializing Views
     */

    private fun initAdapter() {
        adapter = WordListRecyclerViewAdapter(
            longClick = { s: String->
                deleteWord(s)
            },
            click = {
                selectWord(it)
            }
        )
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewList().collect {
                    adapter.updateItem(it)
                }
            }
        }
    }

    private fun initView()
    {
        initAdapter()
        binding.wordListRecyclerview.adapter = adapter
        binding.wordListRecyclerview.layoutManager = LinearLayoutManager(this.context)
    }

    /*
     * Functions
     * 데이터 관리 함수.
     */

    // TODO Collect, First 정확한 사용법 익히기
    private fun selectWord(s : String)
    {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewSameWord(s).first {
                    dlgUtil.wordListFunction(it)
                    true
                }
            }
        }
    }
    private fun deleteWord(s : String)
    {
        dlgUtil.wordDeleteFunction { viewModel.onEvent(WordEvent.Delete(s)) }
    }
}