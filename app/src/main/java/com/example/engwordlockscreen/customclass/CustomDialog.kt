package com.example.engwordlockscreen.customclass

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.adapters.DialogRecyclerViewAdapter
import com.example.engwordlockscreen.database.WordEntity
import com.example.engwordlockscreen.databinding.CustomWordDialogBinding

class CustomDialog(context : Context)
{
    val context = context
    lateinit var binding : CustomWordDialogBinding

    fun wordListFunction(wordList : MutableList<WordEntity>)
    {
        var dlg = Dialog(context)
        binding = CustomWordDialogBinding.inflate(LayoutInflater.from(context))
        dlg.setContentView(binding.root)
        var dlgWin = WindowManager.LayoutParams()
        dlgWin.copyFrom(dlg.window!!.attributes)
        dlgWin.width = WindowManager.LayoutParams.MATCH_PARENT
        dlgWin.height = WindowManager.LayoutParams.WRAP_CONTENT
        var window = dlg.window
        window!!.attributes = dlgWin
        Log.d("wordList size", wordList.size.toString())
        if ( wordList.size != 0) {
            binding.dialogMainWord.text = wordList[0].word
            binding.meanRecyclerview.adapter = DialogRecyclerViewAdapter(wordList, context)
            binding.meanRecyclerview.layoutManager = LinearLayoutManager(context)
        }
        dlg.show()
    }
    fun wordDeleteFunction()
    {

    }
}