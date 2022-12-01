package com.example.engwordlockscreen.presentation.utils.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.databinding.DialogCustomItemBinding
import com.example.engwordlockscreen.domain.database.WordEntities

class DialogRecyclerViewAdapter(wordList : List<WordEntities>, context : Context) : RecyclerView.Adapter<DialogRecyclerViewAdapter.CustomViewHolder>()
{
    inner class CustomViewHolder(var viewBinding : DialogCustomItemBinding) : RecyclerView.ViewHolder(viewBinding.root)

    var wordList = wordList
    var context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder
    {
        var view = DialogCustomItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int)
    {
        holder.viewBinding.dialogPartTextview.text = wordList[position].parts
        holder.viewBinding.dialogMeanTextview.text = wordList[position].mean
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
}