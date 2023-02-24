package com.example.engwordlockscreen.presentation.utils.databinding

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.InverseMethod
import androidx.recyclerview.widget.RecyclerView
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.presentation.main.word.components.insert.WordInsertRecyclerViewAdapter
import kotlinx.coroutines.flow.StateFlow

object BindingAdapter {

    /*
     *  Spinner
     *  View to Model For WordEntities.part
     */
    @JvmStatic
    @InverseBindingAdapter(attribute = "selectedValue", event = "selectItemChangeEvent")
    fun Spinner.getSelectItem() : WordEntities {
        return WordEntities(parts = selectedItem.toString())
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
    fun Spinner.setSelectItem(selectValue : WordEntities) {
        adapter?.run {
            val position = (this as ArrayAdapter<Any>).getPosition(selectValue.parts)
            setSelection(position, true)
            tag = position
        }
    }

    /*
     *  EditText
     *  View To Model For WordEntities.mean
     */

    @JvmStatic
    @InverseBindingAdapter(attribute = "setMeanText", event = "meanTextChanged")
    fun EditText.getMeanText() : WordEntities {
        return WordEntities(mean = text.toString())
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("meanTextChanged")
    fun EditText.setMeanInverseBindingListener(inverseBindingListener: InverseBindingListener) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                inverseBindingListener.onChange()
            }

        })
    }

    @JvmStatic
    @androidx.databinding.BindingAdapter("setMeanText")
    fun EditText.setMeanText(value : WordEntities) {
        val oldText = text.toString()
        if ( oldText == value.mean) {
            setText(value.mean)
        }
    }


    /*
     *  RecyclerView
     */
    @JvmStatic
    @androidx.databinding.BindingAdapter("android:setInsertList")
    fun RecyclerView.setInsertList(list : StateFlow<Any>?) {
        list?.run {
            (adapter as WordInsertRecyclerViewAdapter).setList(value as List<WordEntities>)
        }
    }
}