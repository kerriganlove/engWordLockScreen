package com.example.engwordlockscreen.presentation.utils

import android.content.Context
import android.widget.Toast
import com.example.engwordlockscreen.R

fun Context.showPermissionToast() = Toast.makeText(this, this.getString(R.string.permission_error_text), Toast.LENGTH_LONG).show()
