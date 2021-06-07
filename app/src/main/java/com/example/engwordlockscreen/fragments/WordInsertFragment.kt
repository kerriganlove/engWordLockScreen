package com.example.engwordlockscreen.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.engwordlockscreen.filters.StringFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WordInsertFragment : Fragment() {
    private var inflater : LayoutInflater? = null
    private var mInflater : LinearLayout? = null
    private var insertView : View? = null
    private lateinit var wordDB : WordDatabase
    private var viewCount : Int = 0
    private var binding : FragmentWordInsertBinding? = null
    private var wordList = arrayListOf<WordEntity>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentWordInsertBinding.inflate(inflater,container,false)
        wordDB = WordDatabase.getInstance(requireContext())!!
        init()
        buttonClick()
        return binding?.root
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
        viewCount = 0
    }

    ////////////////////////////////////////// function

    private fun init()
    {
        binding?.wordInsertEdittext?.filters = arrayOf(StringFilter().FilterENG)
    }

    private fun buttonClick()
    {
        binding?.insertFormButton!!.setOnClickListener {
            insertForm()
        }
        binding?.deleteFormButton!!.setOnClickListener {
            removeForm()
        }
        binding?.insertList!!.setOnClickListener {
            insertDB()
        }
    }

    private fun insertForm()
    {
        inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        mInflater = binding?.wordMeanInsert
        insertView = inflater?.inflate(R.layout.design_insert_form,mInflater,false)
        if ( viewCount < 5 ) {
            insertView?.id = 0x8898 + (5 - viewCount)
            insertView?.findViewById<EditText>(R.id.insert_mean_edittext)?.filters = arrayOf(StringFilter().FilterKOR)
            binding?.wordMeanInsert!!.addView(insertView)
            viewCount++
        }
        else {
            Toast.makeText(context,"더 이상 추가하실 수 없습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeForm()
    {
        if ( viewCount > 0) {
            binding?.wordMeanInsert!!.removeViewAt(--viewCount)
        }
        else
        {
            Toast.makeText(context,"더 이상 삭제하실 수 없습니다.",Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertDB()
    {
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
            if ( mean != "" && word != "") {
                wordList.add(wordEntity)
            }
            else { Toast.makeText(context,"단어 또는 뜻이 없어 추가할 수 없습니다.",Toast.LENGTH_SHORT).show() }
        }
        insertList(wordList)
    }

    private fun insertList(wordEntity: ArrayList<WordEntity>)
    {
        CoroutineScope(IO).launch{
            for ( i in wordEntity.indices)
            {
                wordDB.wordDAO().insertWordDB(wordEntity[i])
            }
            withContext(Main){
                Toast.makeText(context,wordEntity[0].word + " 등록 완료.",Toast.LENGTH_LONG).show()
                wordList.clear()
            }
        }
    }

    private fun insertByDic()
    {

    }

}