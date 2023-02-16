package com.example.engwordlockscreen.presentation.utils.dialogs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.databinding.DialogCustomItemBinding
import com.example.engwordlockscreen.domain.database.WordEntities

class WordListDialogRecyclerViewAdapter(val wordList: List<WordEntities>) : RecyclerView.Adapter<WordListDialogRecyclerViewAdapter.CustomViewHolder>()
{
    inner class CustomViewHolder(var binding : DialogCustomItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : WordEntities) {
            binding.apply {
                dialogPartTextview.text = item.parts
                dialogMeanTextview.text = item.mean
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        var view = DialogCustomItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    override fun getItemCount(): Int {
        return wordList.size
    }
}