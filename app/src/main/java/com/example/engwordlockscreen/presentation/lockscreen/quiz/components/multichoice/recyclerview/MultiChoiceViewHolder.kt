package com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.databinding.ListMultichoiceItemBinding
import com.example.engwordlockscreen.domain.database.WordEntities

class MultiChoiceViewHolder(
    private val binding : ListMultichoiceItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : WordEntities) {
        binding.textPart.text = item.parts
        binding.textMean.text = item.mean
    }

    private fun initListener() {

    }
}