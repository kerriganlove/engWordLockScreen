package com.example.engwordlockscreen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.FragmentWordInsertBinding

class WordInsertFragment : Fragment() {
    var binding : FragmentWordInsertBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentWordInsertBinding.inflate(inflater,container,false)
        return binding?.root
    }

}