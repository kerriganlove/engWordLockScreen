package com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.databinding.ListMultichoiceItemBinding
import com.example.engwordlockscreen.domain.database.WordEntities

class MultiChoiceViewHolder(
    private val binding : ListMultichoiceItemBinding,
    private val onClick : (Any) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : WordEntities) {
        binding.textPart.text = item.parts
        binding.textMean.text = item.mean
        initListener(item)
    }

    private fun initListener(item : Any) {
        binding.root.setOnClickListener{ onClick.invoke(item) }
    }
}