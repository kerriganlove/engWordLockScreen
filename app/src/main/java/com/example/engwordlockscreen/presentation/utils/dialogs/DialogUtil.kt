package com.example.engwordlockscreen.presentation.utils.dialogs

import android.util.Log
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.engwordlockscreen.constants.DialogTag
import kotlinx.coroutines.delay

// 구조 개선이 필요해보이지만.. 흠...
object DialogUtil {
    private lateinit var curDialog : DialogFragment // last Showed Dialog

    suspend fun showDialog(
        tag : DialogTag,
        fm : FragmentManager,
        onDismissFunc : () -> Unit = {},
        delayTime : Long = 0)
    {
        when(tag) {
            DialogTag.CorrectAnswerDialog -> {
                CorrectDialogFragment(onDismissFunc).apply {
                    curDialog = this
                    show(fm, null)
                    delay(delayTime)
                    dismiss()
                }
            }
            DialogTag.LoadingDialog -> {
                LoadingDialogFragment().apply {
                    curDialog = this
                    Log.d("====Cur Dialog", "$curDialog")
                    show(fm, null)
                }
            }
        }
    }

    fun dismissDialog(tag: DialogTag) {
        when (tag) {
            DialogTag.CorrectAnswerDialog ->
                if (curDialog is CorrectDialogFragment) {
                    if (curDialog.dialog?.isShowing == true) {
                        curDialog.dismiss()
                    }
                }
            DialogTag.LoadingDialog ->
                if (curDialog is LoadingDialogFragment) {
                    if (curDialog.dialog?.isShowing == true) {
                        curDialog.dismiss()
                    }
                }
        }
    }
}
