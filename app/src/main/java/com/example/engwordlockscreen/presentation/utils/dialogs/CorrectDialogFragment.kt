package com.example.engwordlockscreen.presentation.utils.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.constants.CustomConst

class CorrectDialogFragment(
    private val onDismissFunction : () -> Unit = {},
    @StringRes id : Int = -1
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).setView(R.layout.dialog_correct_loading).setCancelable(false).create()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissFunction.invoke()
    }
}