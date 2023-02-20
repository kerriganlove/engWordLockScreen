package com.example.engwordlockscreen.presentation.utils.databinding

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.presentation.main.word.components.insert.WordInsertRecyclerViewAdapter
import kotlinx.coroutines.flow.StateFlow

object BindingAdapter {

    /*
     *  Spinner
     *  View to Model
     */
    @JvmStatic
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectItemChangeEvent")
    fun Spinner.getSelectItem() : Any? {
        return selectedItem
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("selectItemChangeEvent")
    fun Spinner.setInverseBindingListener(inverseBindingListener: InverseBindingListener) {
        inverseBindingListener.run {
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    inverseBindingListener.onChange()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }
        }
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("selectedValue")
    fun Spinner.setSelectItem(selectValue : StateFlow<Any>?) {
        adapter?.run {
            val position = (this as ArrayAdapter<Any>).getPosition(selectValue)
            setSelection(position, false)
        }
    }

    /*
     *  EditText
     *  View To Model
     */


    /*
     *  RecyclerView
     */
    @JvmStatic
    @androidx.databinding.BindingAdapter("android:setInsertList")
    fun RecyclerView.setInsertList(list : StateFlow<Any>?) {
        Log.d("before insertList", "$list")
        list?.run {
            Log.d("before", "$value")
            (adapter as WordInsertRecyclerViewAdapter).setList(value as List<WordEntities>)
        }
    }

}