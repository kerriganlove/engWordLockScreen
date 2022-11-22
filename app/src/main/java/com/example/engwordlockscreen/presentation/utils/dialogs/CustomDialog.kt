package com.example.engwordlockscreen.presentation.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.databinding.CustomGeneralDialogBinding
import com.example.engwordlockscreen.databinding.CustomWordDialogBinding
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.presentation.utils.recyclerview.adapters.DialogRecyclerViewAdapter

class CustomDialog(val context: Context)
{
    lateinit var binding : CustomWordDialogBinding
    lateinit var deleteBinding : CustomGeneralDialogBinding

    fun wordListFunction(wordList : List<WordEntities>)
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

    fun alertPermissionDialog( click : () -> Unit ) {

    }

    fun wordDeleteFunction(click : () -> Unit)
    {
        val dlg = Dialog(context)
        deleteBinding = CustomGeneralDialogBinding.inflate(LayoutInflater.from(context))
        dlg.setContentView(deleteBinding.root)
        val dlgWin = WindowManager.LayoutParams()
        dlgWin.copyFrom(dlg.window!!.attributes)
        dlgWin.width = WindowManager.LayoutParams.MATCH_PARENT
        dlgWin.height = WindowManager.LayoutParams.WRAP_CONTENT
        val window = dlg.window
        window!!.attributes = dlgWin
        dlg.show()
        deleteBinding.deleteOkButton.setOnClickListener {
            dlg.dismiss()
            if ( !dlg.isShowing) {
                click.invoke()
            }
        }
        deleteBinding.deleteCancelButton.setOnClickListener {
            dlg.dismiss()
        }
    }

    fun checkPermissionDialog(click : () -> Unit) {
        val dlg = Dialog(context)
        deleteBinding = CustomGeneralDialogBinding.inflate(LayoutInflater.from(context))
        dlg.setContentView(deleteBinding.root)
        val dlgWin = WindowManager.LayoutParams()
        dlgWin.copyFrom(dlg.window!!.attributes)
        dlgWin.width = WindowManager.LayoutParams.MATCH_PARENT
        dlgWin.height = WindowManager.LayoutParams.WRAP_CONTENT
        val window = dlg.window
        window!!.attributes = dlgWin
        dlg.show()
        deleteBinding.deleteOkButton.setOnClickListener {
            dlg.dismiss()
            click.invoke()
        }
        deleteBinding.deleteCancelButton.setOnClickListener {
            dlg.dismiss()
        }
    }
}