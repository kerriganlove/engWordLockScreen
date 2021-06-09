package com.example.engwordlockscreen.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.database.WordDatabase
import com.example.engwordlockscreen.database.WordEntity
import com.example.engwordlockscreen.databinding.FragmentWordListBinding
import com.example.engwordlockscreen.databinding.ListCustomItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WordListFragment : Fragment() {

    var startList : MutableList<WordEntity> = mutableListOf()
    var wordList : MutableList<WordEntity> = mutableListOf()
    lateinit var wordDB : WordDatabase
    var binding : FragmentWordListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWordListBinding.inflate(inflater,container,false)
        wordDB = WordDatabase.getInstance(requireContext())!!
        init()
        return binding!!.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun init()
    {
        binding!!.wordListRecyclerview.adapter = WordListRecyclerViewAdapter()
        binding!!.wordListRecyclerview.layoutManager = LinearLayoutManager(activity)
        //binding?.wordListRecyclerview?.addOnItemTouchListener()
    }

    inner class WordListRecyclerViewAdapter : RecyclerView.Adapter<WordListRecyclerViewAdapter.CustomViewHolder>()
    {
        init {
            startList.clear()
            wordList.clear()
            selectList()
            notifyDataSetChanged()
        }
        inner class CustomViewHolder(var viewBinding : ListCustomItemBinding) : RecyclerView.ViewHolder(viewBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            var view = ListCustomItemBinding.inflate(LayoutInflater.from(context),parent,false)
            return CustomViewHolder(view)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.viewBinding.listWordTextview.text = startList[position].word
            Log.d("Data 11",holder.viewBinding.listWordTextview.text.toString())
            holder.viewBinding.listPartTextview.text = startList[position].parts
            holder.viewBinding.listMeanTextview.text = startList[position].mean
        }

        override fun getItemCount(): Int {
            return startList.size
        }

    }

    private fun selectList()
    {
        CoroutineScope(IO).launch {
            var view = wordDB.wordDAO().viewList()
            startList = view
            WordListRecyclerViewAdapter().notifyDataSetChanged()
        }
    }
}