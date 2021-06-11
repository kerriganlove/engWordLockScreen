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
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

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
        selectList()
        //binding?.wordListRecyclerview?.addOnItemTouchListener()
    }

    inner class WordListRecyclerViewAdapter : RecyclerView.Adapter<WordListRecyclerViewAdapter.CustomViewHolder>()
    {
        init {
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
        wordDB.wordDAO().viewList().observe(viewLifecycleOwner,androidx.lifecycle.Observer {
            startList.clear()
            wordList.clear()
            startList = it
            binding!!.wordListRecyclerview.adapter = WordListRecyclerViewAdapter()
            binding!!.wordListRecyclerview.layoutManager = LinearLayoutManager(activity)
            Log.d("Hi ",startList[startList.size-1].word)
        })
    }
}