package com.example.engwordlockscreen.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.database.WordDatabase
import com.example.engwordlockscreen.database.WordEntity
import com.example.engwordlockscreen.databinding.FragmentWordInsertBinding
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class WordInsertFragment : Fragment() {
    private var inflater : LayoutInflater? = null
    private var mInflater : LinearLayout? = null
    private var insertView : View? = null
    private lateinit var wordDB : WordDatabase
    private var viewCount : Int = 0
    private var binding : FragmentWordInsertBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentWordInsertBinding.inflate(inflater,container,false)
        wordDB = WordDatabase.getInstance(requireContext())!!
        buttonClick()
        return binding?.root
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
        viewCount = 0
    }

    ////////////////////////////////////////// function

    private fun buttonClick()
    {
        binding?.insertFormButton!!.setOnClickListener {
            insertForm()
        }
        binding?.insertList!!.setOnClickListener {
            for (i in binding?.wordMeanInsert?.children!!) {
                var meanId =
                    i.findViewById<RelativeLayout>(i.id)
                        ?.findViewById<EditText>(R.id.insert_mean_edittext)
                var partId =
                    i.findViewById<RelativeLayout>(i.id)
                        ?.findViewById<Spinner>(R.id.insert_form_spinner)
                var mean = meanId?.text.toString()
                var part = partId?.selectedItem.toString()
                var word = binding?.wordInsertEdittext?.text.toString()
                var wordEntity = WordEntity(0,word,part,mean)
                insertList(wordEntity)
            }
        }
    }
    private fun insertForm()
    {
        inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        mInflater = binding?.wordMeanInsert
        insertView = inflater?.inflate(R.layout.design_insert_form,mInflater,false)
        if ( viewCount < 5 ) {
            insertView?.id = 0x8898 + (5 - viewCount)
            binding?.wordMeanInsert!!.addView(insertView)
            viewCount++
        }
        else {
            Toast.makeText(context,"더 이상 추가하실 수 없습니다.",Toast.LENGTH_LONG).show()
        }
    }
    private fun insertList(wordEntity: WordEntity)
    {
        runBlocking {
            wordDB.wordDAO().insertWordDB(wordEntity)
            Toast.makeText(context,"등록 완료",Toast.LENGTH_LONG).show()
        }
    }
}