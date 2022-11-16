package com.example.engwordlockscreen.presentation.utils.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity

class WordListDiffUtil : DiffUtil.ItemCallback<WordEntity>() {
    override fun areItemsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
        return oldItem == newItem
    }
}