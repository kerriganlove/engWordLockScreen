package com.example.engwordlockscreen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.engwordlockscreen.data.datasource.WordDatabase
import com.example.engwordlockscreen.databinding.FragmentSettingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingFragment : Fragment() {

    private lateinit var binding : FragmentSettingBinding
    private lateinit var wordDB : WordDatabase
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        wordDB = WordDatabase.getInstance(requireContext())!!
        buttonClick()
        return binding.root
    }

    private fun buttonClick()
    {
        binding.clearDbAll.setOnClickListener {
            clearDB()
        }
    }
    private fun clearDB()
    {
        CoroutineScope(IO).launch{
            wordDB.clearAllTables()
            withContext(Main){
                Toast.makeText(context,"단어장 초기화가 완료되었습니다!",Toast.LENGTH_SHORT).show()
            }
        }
    }
}