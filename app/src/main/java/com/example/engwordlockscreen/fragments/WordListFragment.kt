package com.example.engwordlockscreen.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.adapters.WordListRecyclerViewAdapter
import com.example.engwordlockscreen.customclass.CustomDialog
import com.example.engwordlockscreen.database.WordDatabase
import com.example.engwordlockscreen.database.WordEntity
import com.example.engwordlockscreen.databinding.FragmentWordListBinding
import com.example.engwordlockscreen.databinding.ListCustomItemBinding
import com.example.engwordlockscreen.listeners.RecyclerItemClickListener

class WordListFragment : Fragment() {

    var startList : MutableList<WordEntity> = mutableListOf()
    var wordList : MutableList<WordEntity> = mutableListOf()
    lateinit var wordDB : WordDatabase
    var binding : FragmentWordListBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWordListBinding.inflate(inflater,container,false)
        init()
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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
                Log.d("ITEMLONGCLICK","Hi Clicked")
                Toast.makeText(requireContext(),"Hi Long Clicked",Toast.LENGTH_LONG)
            }
        }))
        selectList()
    }


    private fun selectList()
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
}