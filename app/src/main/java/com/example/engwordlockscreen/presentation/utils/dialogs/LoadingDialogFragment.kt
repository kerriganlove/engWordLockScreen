package com.example.engwordlockscreen.presentation.utils.dialogs

import android.app.ActionBar.LayoutParams
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.constants.CustomConst


class LoadingDialogFragment(
    private val onDismissFunction : () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = when(tag) {
        CustomConst.CORRECT_LOADING_DIALOG_TAG -> {
            AlertDialog.Builder(requireContext()).setView(R.layout.dialog_correct_loading).setCancelable(false).create()
        }
        else -> {
            AlertDialog.Builder(requireContext()).create()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissFunction.invoke()
    }
}