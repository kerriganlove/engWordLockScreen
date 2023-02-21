package com.example.engwordlockscreen.presentation.main.word.components.insert

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.DesignInsertFormBinding
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.presentation.utils.string.StringFilter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WordInsertRecyclerViewAdapter(
    private val insertFormChange : (Int, WordEntities) -> Unit
) : RecyclerView.Adapter<WordInsertRecyclerViewAdapter.InsertViewHolder>(){

    private var list : List<WordEntities> = listOf()

    inner class InsertViewHolder(private val binding : DesignInsertFormBinding) : RecyclerView.ViewHolder(binding.root){
        private lateinit var wordFlow : MutableStateFlow<WordEntities>
        private var job : Job? = null
        fun bind() {
            binding.apply {
                wordFlow = MutableStateFlow(list[adapterPosition])
                word = wordFlow
                insertMeanEdittext.filters = arrayOf(StringFilter.filterKOR)
            }
        }
        fun clear() {
            job?.cancel()
            binding.apply {
                insertFormSpinner.setSelection(0)
                insertMeanEdittext.setText("")
            }
        }
        fun collectStart() {
            job = binding.root.findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
               wordFlow.asStateFlow().collect { word ->
                   insertFormChange.invoke(adapterPosition, word)
               }
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

    override fun onViewAttachedToWindow(holder: InsertViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.collectStart()
    }

    override fun onViewDetachedFromWindow(holder: InsertViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.clear()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list : List<WordEntities>) {
        this.list = list
    }
}