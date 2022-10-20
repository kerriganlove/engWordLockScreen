package com.example.engwordlockscreen.presentation.word.components

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.presentation.utils.recyclerview.adapters.WordListRecyclerViewAdapter
import com.example.engwordlockscreen.presentation.utils.CustomDialog
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.databinding.FragmentWordListBinding
import com.example.engwordlockscreen.presentation.word.WordEvent
import com.example.engwordlockscreen.presentation.word.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordListFragment : Fragment() {

    /*
     * Values
     */
    private var _binding : FragmentWordListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<WordViewModel>()
    private lateinit var adapter : WordListRecyclerViewAdapter

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
        adapter = WordListRecyclerViewAdapter( { deleteWord("") }, { selectWord() })
    }

    private fun initView()
    {
        initAdapter()
        binding!!.wordListRecyclerview.adapter = adapter
        binding!!.wordListRecyclerview.layoutManager = LinearLayoutManager(activity)
        selectList()
    }

    /*
     * Functions
     * 데이터 관리 함수.
     */

    private fun selectList() {
        viewModel.viewList().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.updateItem(it!!)
        })
    }
    private fun selectWord(s : String)
    {
        viewModel.onEvent(WordEvent.SameWord(s))
        lifecycleScope.launch {
            var dlg = CustomDialog(requireContext())
            dlg.wordListFunction(wordList)
        }
    }
    private fun deleteWord(s : String, pos : Int)
    {
        viewModel.onEvent(WordEvent.Delete(s))
    }
}