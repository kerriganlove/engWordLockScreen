package com.example.engwordlockscreen.presentation.word.components

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.presentation.utils.recyclerview.adapters.WordListRecyclerViewAdapter
import com.example.engwordlockscreen.presentation.utils.CustomDialog
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.databinding.FragmentWordListBinding
import com.example.engwordlockscreen.databinding.ListCustomItemBinding
import com.example.engwordlockscreen.presentation.word.WordEvent
import com.example.engwordlockscreen.presentation.word.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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
    private var list = ArrayList<WordEntity>()

    /*
     * LifeCycle Callback
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordListBinding.inflate(inflater,container,false)
        initView()
        list.add(WordEntity(0,"","","1234"))
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
            longClick = { s: String, pos: Int ->
                deleteWord(s, pos)
            },
            click = {
                selectWord(it)
            }
        )
        lifecycleScope.launch {
            viewModel.viewList().collect {
                adapter.updateItem(it)
            }
        }
        lifecycleScope.launch {

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

    private fun selectWord(s : String)
    {
        viewModel.onEvent(WordEvent.SameWord(s))


    }
    private fun deleteWord(s : String, pos : Int)
    {

         viewModel.onEvent(WordEvent.Delete(s))
    }
}