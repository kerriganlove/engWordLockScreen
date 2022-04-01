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
import com.example.engwordlockscreen.adapters.WordListRecyclerViewAdapter
import com.example.engwordlockscreen.presentation.utils.CustomDialog
import com.example.engwordlockscreen.data.datasource.WordDatabase
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.databinding.FragmentWordListBinding
import com.example.engwordlockscreen.di.AppModule
import com.example.engwordlockscreen.domain.repository.WordRepository
import com.example.engwordlockscreen.listeners.RecyclerItemClickListener
import com.example.engwordlockscreen.presentation.main.MainViewModel
import com.example.engwordlockscreen.presentation.word.WordEvent
import com.example.engwordlockscreen.presentation.word.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class WordListFragment : Fragment() {

    var startList : MutableList<WordEntity> = mutableListOf()
    var wordList : MutableList<WordEntity> = mutableListOf()
    private var _binding : FragmentWordListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<WordViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordListBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun init()
    {
        binding!!.wordListRecyclerview.adapter = WordListRecyclerViewAdapter(startList, requireContext())
        binding!!.wordListRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding!!.wordListRecyclerview.addOnItemTouchListener(RecyclerItemClickListener(requireContext(), binding!!.wordListRecyclerview, object : RecyclerItemClickListener.OnItemClickListener
        {
            override fun onItemClick(view: View, position: Int) {
                Log.d("wordListLog",startList[position].word)
                selectWord(startList[position].word)
            }

            override fun onItemLongClick(view: View, position: Int) {
                var dlg = CustomDialog(requireContext())
                dlg.wordDeleteFunction()
                dlg.onClickedListener(object : CustomDialog.ButtonClicked
                {
                    override fun onClicked() {
                        deleteWord(startList[position].word,position)
                    }
                })
            }
        }))
        selectList()
    }

    private fun selectList()
    {
        viewModel.viewList().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            startList.clear()
            if ( it != null) startList.addAll(it)
            else Log.d("Why null","Fuck")
            binding!!.wordListRecyclerview.adapter!!.notifyDataSetChanged()
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
        lifecycleScope.launch {
            startList.removeAt(pos)
            binding!!.wordListRecyclerview.adapter!!.notifyItemRemoved(pos)
            binding!!.wordListRecyclerview.adapter!!.notifyDataSetChanged()
            Log.d("Hi_Delete","Is Click?")
        }
    }
}