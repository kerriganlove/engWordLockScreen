package com.example.engwordlockscreen.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.adapters.WordListRecyclerViewAdapter
import com.example.engwordlockscreen.presentation.utils.CustomDialog
import com.example.engwordlockscreen.data.datasource.WordDatabase
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.databinding.FragmentWordListBinding
import com.example.engwordlockscreen.listeners.RecyclerItemClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WordListFragment : Fragment() {

    var startList : MutableList<WordEntity> = mutableListOf()
    var wordList : MutableList<WordEntity> = mutableListOf()
    lateinit var wordDB : WordDatabase
    private var _binding : FragmentWordListBinding? = null
    private val binding get() = _binding!!


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
        wordDB = WordDatabase.getInstance(requireContext())!!
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

    private suspend fun selectList()
    {
        wordDB.wordDAO().viewList().observe(viewLifecycleOwner,androidx.lifecycle.Observer {
            startList.clear()
            startList.addAll(it)
            binding!!.wordListRecyclerview.adapter!!.notifyDataSetChanged()
        })
    }
    private fun selectWord(s : String)
    {
        wordDB.wordDAO().viewSameWord(s).observe(viewLifecycleOwner, {
            wordList.clear()
            wordList.addAll(it)
            var dlg = CustomDialog(requireContext())
            dlg.wordListFunction(wordList)
        })
    }
    fun deleteWord(s : String, pos : Int)
    {
        CoroutineScope(IO).launch {
            wordDB.wordDAO().deleteSameWords(s)
            withContext(Main){
                startList.removeAt(pos)
                binding!!.wordListRecyclerview.adapter!!.notifyItemRemoved(pos)
                binding!!.wordListRecyclerview.adapter!!.notifyDataSetChanged()
                Log.d("Hi_Delete","Is Click?")
            }
        }
    }
}