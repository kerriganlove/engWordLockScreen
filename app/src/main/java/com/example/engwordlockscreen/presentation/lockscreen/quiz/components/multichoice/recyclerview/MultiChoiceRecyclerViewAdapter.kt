package com.example.engwordlockscreen.presentation.lockscreen.quiz.components.multichoice.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.databinding.ListMultichoiceItemBinding
import com.example.engwordlockscreen.domain.database.WordEntities

class MultiChoiceRecyclerViewAdapter : RecyclerView.Adapter<MultiChoiceViewHolder>() {

    private lateinit var binding : ListMultichoiceItemBinding
    private var itemList : MutableList<WordEntities> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiChoiceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ListMultichoiceItemBinding.inflate(layoutInflater, parent, false)
        return MultiChoiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MultiChoiceViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        Log.d("TRANSFER", "${itemList.size}")
        return itemList.size
    }

    /*
      * functions
      * 데이터 리스트 처리 함수
      */
    fun setList(list : List<WordEntities>) {
        Log.d("TRANSFER", "${itemList.addAll(list)}")
        Log.d("TRANSFER","$itemList")
    }
}