package com.example.engwordlockscreen.presentation.main.word.components.insert

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.DesignInsertFormBinding
import com.example.engwordlockscreen.domain.database.WordEntities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordInsertRecyclerViewAdapter @Inject constructor(
    private val externalScope : CoroutineScope
) : RecyclerView.Adapter<WordInsertRecyclerViewAdapter.InsertViewHolder>(){

    private var list : List<WordEntities> = listOf()

    inner class InsertViewHolder(private val binding : DesignInsertFormBinding) : RecyclerView.ViewHolder(binding.root){
        private var meanFlow = MutableStateFlow("")
        private var partFlow = MutableStateFlow("")
        init {
            externalScope.launch {
                Log.d("===========", meanFlow.value)
                Log.d("===========", partFlow.value)
            }
        }
        fun bind() {
            binding.apply {
                part = partFlow
                mean = meanFlow
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsertViewHolder {
        val binding = DataBindingUtil.inflate<DesignInsertFormBinding>(LayoutInflater.from(parent.context), R.layout.design_insert_form, parent, false)
        return InsertViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InsertViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list : List<WordEntities>) {
        Log.d("before list", "$list")
        this.list = list
        Log.d("after list", "$this.list")
    }
}