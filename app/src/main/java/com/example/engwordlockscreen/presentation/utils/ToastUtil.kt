package com.example.engwordlockscreen.presentation.utils

import android.content.Context
import android.widget.Toast
import com.example.engwordlockscreen.R

object ToastUtil {

    fun permissionToast(context : Context) = Toast.makeText(context, context.getString(R.string.permission_error_text), Toast.LENGTH_LONG).show()


}