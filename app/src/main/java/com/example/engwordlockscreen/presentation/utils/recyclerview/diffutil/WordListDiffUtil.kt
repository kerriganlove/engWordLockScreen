package com.example.engwordlockscreen.presentation.utils.recyclerview.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.engwordlockscreen.domain.database.WordEntities

class WordListDiffUtil : DiffUtil.ItemCallback<WordEntities>() {
    override fun areItemsTheSame(oldItem: WordEntities, newItem: WordEntities): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: WordEntities, newItem: WordEntities): Boolean {
        return oldItem == newItem
    }
}