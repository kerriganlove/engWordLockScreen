package com.example.engwordlockscreen.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.FragmentWordInsertBinding


class WordInsertFragment : Fragment() {
    private var viewCount : Int = 0
    private var binding : FragmentWordInsertBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentWordInsertBinding.inflate(inflater,container,false)
        buttonClick()
        return binding?.root
    }
    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    ////////////////////////////////////////// function

    private fun buttonClick()
    {
        binding?.insertFormButton!!.setOnClickListener {
            insertForm()
        }
        binding?.insertList!!.setOnClickListener {
            insertList()
        }
    }
    private fun insertForm()
    {
        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val mInflater = binding?.wordMeanInsert
        val insertView = inflater?.inflate(R.layout.design_insert_form,mInflater,false)
        if ( viewCount < 5 ) {
            binding?.wordMeanInsert!!.addView(insertView)
            viewCount++
        }
        else {
            Toast.makeText(context,"더 이상 추가하실 수 없습니다.",Toast.LENGTH_LONG).show()
        }
    }
    private fun insertList()
    {

    }

}