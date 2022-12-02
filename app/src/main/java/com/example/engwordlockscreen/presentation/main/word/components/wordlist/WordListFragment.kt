package com.example.engwordlockscreen.presentation.main.word.components.wordlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.databinding.FragmentWordListBinding
import com.example.engwordlockscreen.presentation.utils.dialogs.CustomDialog
import com.example.engwordlockscreen.presentation.main.word.WordEvent
import com.example.engwordlockscreen.presentation.main.WordViewModel
import com.example.engwordlockscreen.presentation.main.word.components.wordlist.recyclerview.WordListRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordListFragment : Fragment() {

    /*
     * Values
     */

    private var _binding : FragmentWordListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<WordViewModel>()
    private lateinit var adapter : WordListRecyclerViewAdapter
    private val dlgUtil by lazy { CustomDialog(requireContext())}

    /*
     * LifeCycle Callback
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
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
            var wordlist = viewModel.viewSameWord(s).first()
            dlgUtil.wordListFunction(wordlist)
            Log.d("TRANSFER", "${wordlist}")
        }
    }
    private fun deleteWord(s: String)
    {
        dlgUtil.wordDeleteFunction { viewModel.onEvent(WordEvent.Delete(s)) }
    }
}