package com.example.engwordlockscreen.presentation.utils.databinding

import android.widget.Spinner
import androidx.databinding.InverseBindingAdapter

object BindingAdapter {

    @JvmStatic
    @InverseBindingAdapter( attribute = "selectValue", event = "selectItemChangeEvent" )
    fun Spinner.getSelectItem() {

    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("selectItemChangeEvent")
    fun Spinner.setInverseBindingListener() {

    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("selectValue")
    fun Spinner.setSelectItem() {

    }
}