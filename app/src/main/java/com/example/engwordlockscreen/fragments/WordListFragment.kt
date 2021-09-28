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
        binding!!.wordListRecyclerview.adapter = WordListRecyclerViewAdapter()
        binding!!.wordListRecyclerview.layoutManager = LinearLayoutManager(activity)
        selectList()
        //binding?.wordListRecyclerview?.addOnItemTouchListener()
    }

    inner class WordListRecyclerViewAdapter : RecyclerView.Adapter<WordListRecyclerViewAdapter.CustomViewHolder>()
    {
        inner class CustomViewHolder(var viewBinding : ListCustomItemBinding) : RecyclerView.ViewHolder(viewBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            var view = ListCustomItemBinding.inflate(LayoutInflater.from(context),parent,false)
            Log.d("Data22",view.listWordTextview.text.toString())
            return CustomViewHolder(view)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.viewBinding.listWordTextview.text = startList[position].word
            holder.viewBinding.listPartTextview.text = startList[position].parts
            holder.viewBinding.listMeanTextview.text = startList[position].mean
            Log.d("Data 11",holder.viewBinding.listWordTextview.text.toString())
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
            startList.addAll(it)
            binding!!.wordListRecyclerview.adapter!!.notifyDataSetChanged()
            var itemCount = binding!!.wordListRecyclerview.adapter!!.itemCount
            Log.d("Hi ",startList[itemCount-1].word)
        })
    }
}