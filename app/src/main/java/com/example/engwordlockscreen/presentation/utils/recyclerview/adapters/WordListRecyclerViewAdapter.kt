package com.example.engwordlockscreen.presentation.utils.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.databinding.ListCustomItemBinding
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.presentation.utils.recyclerview.diffutil.WordListDiffUtil
import com.example.engwordlockscreen.presentation.utils.recyclerview.viewholder.WordListViewHolder

class WordListRecyclerViewAdapter(
    private val longClick : () -> Unit,
    private val click : () -> Unit
) : ListAdapter<WordEntity, RecyclerView.ViewHolder>(diffUtil) {

    /*
     * Values
     */
    private lateinit var binding : ListCustomItemBinding
    private lateinit var holder : WordListViewHolder

    /*
     * Singleton
     */
    companion object {
        val diffUtil = WordListDiffUtil()
    }

    /*
     * Override
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ListCustomItemBinding.inflate(layoutInflater, parent, false)
        return WordListViewHolder(binding, longClick, click).also { holder = it }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as WordListViewHolder).bind(currentList[position])
    }

    /*
     * functions
     * 데이터 리스트 처리 함수
     */

    fun updateItem(list : MutableList<WordEntity>) {
        submitList(list)
    }
}