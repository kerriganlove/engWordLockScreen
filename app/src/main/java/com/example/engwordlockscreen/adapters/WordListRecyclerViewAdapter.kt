package com.example.engwordlockscreen.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.databinding.ListCustomItemBinding

class WordListRecyclerViewAdapter(startList : MutableList<WordEntity>, context : Context) : RecyclerView.Adapter<WordListRecyclerViewAdapter.CustomViewHolder>()
{
    var startList = startList
    var context = context
    inner class CustomViewHolder(var viewBinding : ListCustomItemBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var view = ListCustomItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.viewBinding.listWordTextview.text = startList[position].word
        holder.viewBinding.listPartTextview.text = startList[position].parts
        holder.viewBinding.listMeanTextview.text = startList[position].mean
    }

    override fun getItemCount(): Int {
        return startList.size
    }
}