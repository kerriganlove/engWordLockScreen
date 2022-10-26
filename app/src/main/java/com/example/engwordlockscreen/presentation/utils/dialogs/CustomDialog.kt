package com.example.engwordlockscreen.presentation.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.presentation.utils.recyclerview.adapters.DialogRecyclerViewAdapter
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.databinding.CustomDeleteDialogBinding
import com.example.engwordlockscreen.databinding.CustomWordDialogBinding

class CustomDialog(val context: Context)
{
    lateinit var binding : CustomWordDialogBinding
    lateinit var deleteBinding : CustomDeleteDialogBinding

    fun wordListFunction(wordList : MutableList<WordEntity>)
    {
        val dlg = Dialog(context)
        binding = CustomWordDialogBinding.inflate(LayoutInflater.from(context))
        dlg.setContentView(binding.root)
        val dlgWin = WindowManager.LayoutParams()
        dlgWin.copyFrom(dlg.window!!.attributes)
        dlgWin.width = WindowManager.LayoutParams.MATCH_PARENT
        dlgWin.height = WindowManager.LayoutParams.WRAP_CONTENT
        val window = dlg.window
        window!!.attributes = dlgWin
        Log.d("wordList size", wordList.size.toString())
        if ( wordList.size != 0) {
            binding.dialogMainWord.text = wordList[0].word
            binding.meanRecyclerview.adapter = DialogRecyclerViewAdapter(wordList, context)
            binding.meanRecyclerview.layoutManager = LinearLayoutManager(context)
            dlg.show()
        }
    }

    fun wordDeleteFunction(click : () -> Unit)
    {
        val dlg = Dialog(context)
        deleteBinding = CustomDeleteDialogBinding.inflate(LayoutInflater.from(context))
        dlg.setContentView(deleteBinding.root)
        val dlgWin = WindowManager.LayoutParams()
        dlgWin.copyFrom(dlg.window!!.attributes)
        dlgWin.width = WindowManager.LayoutParams.MATCH_PARENT
        dlgWin.height = WindowManager.LayoutParams.WRAP_CONTENT
        val window = dlg.window
        window!!.attributes = dlgWin
        dlg.show()
        deleteBinding.deleteOkButton.setOnClickListener {
            click.invoke()
            dlg.dismiss()
        }
        deleteBinding.deleteCancelButton.setOnClickListener {
            dlg.dismiss()
        }
    }
}