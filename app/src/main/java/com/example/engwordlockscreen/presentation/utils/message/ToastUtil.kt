package com.example.engwordlockscreen.presentation.utils

import android.content.Context
import android.widget.Toast
import com.example.engwordlockscreen.R

fun Context.showPermissionToast() = Toast.makeText(this, this.getString(R.string.permission_error_text), Toast.LENGTH_LONG).show()

fun Context.showInsertCompleteToast(word : String) = Toast.makeText(this, String.format(this.getString(R.string.insert_complete_text), word), Toast.LENGTH_SHORT).show()

fun Context.showInsertErrorToast(word : String) = Toast.makeText(this, String.format(this.getString(R.string.insert_error_text), word), Toast.LENGTH_SHORT).show()