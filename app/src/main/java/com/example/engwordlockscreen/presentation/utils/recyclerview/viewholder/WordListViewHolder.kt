package com.example.engwordlockscreen.presentation.utils.recyclerview.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.databinding.ListCustomItemBinding
import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity

/*
 * 단어 목록 RecyclerView 뷰홀더
 * longClick -> longClick 시 실행될 함수
 * Click -> Click 시 실행될 함수
 */
class WordListViewHolder(private val binding : ListCustomItemBinding,
    private val longClick : (String) -> Unit,
    private val click : (String) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(wordEntity : WordEntity) {
        binding.apply {
            listWordTextview.text = wordEntity.word
            listMeanTextview.text = wordEntity.mean
            listPartTextview.text = wordEntity.parts
            initListener()
        }
    }

    /*
     * function
     * initialize Listener
     */
    private fun initListener() {
        binding.root.apply {
            setOnLongClickListener {
                longClick.invoke(binding.listWordTextview.text.toString())
                true
            }
            setOnClickListener {
                click.invoke(binding.listWordTextview.text.toString())
            }
        }
    }
}