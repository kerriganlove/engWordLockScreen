package com.example.engwordlockscreen.presentation.word.components

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.data.datasource.WordDatabase
import com.example.engwordlockscreen.data.repository.WordRepositoryImpl
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.databinding.FragmentWordInsertBinding
import com.example.engwordlockscreen.domain.repository.WordRepository
import com.example.engwordlockscreen.domain.usecase.DeleteAllWordUseCase
import com.example.engwordlockscreen.domain.usecase.WordUseCases
import com.example.engwordlockscreen.presentation.main.MainViewModel
import com.example.engwordlockscreen.presentation.utils.StringFilter
import com.example.engwordlockscreen.presentation.word.WordEvent
import com.example.engwordlockscreen.presentation.word.WordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordInsertFragment : Fragment() {
    private var inflater : LayoutInflater? = null
    private var mInflater : LinearLayout? = null
    private var insertView : View? = null
    private lateinit var wordDB : WordDatabase
    private var viewCount : Int = 0
    private var binding : FragmentWordInsertBinding? = null
    private var wordList = arrayListOf<WordEntity>()
    private val viewModel by viewModels<WordViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentWordInsertBinding.inflate(inflater,container,false)
        init()
        buttonClick()
        return binding?.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
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
        val word = binding?.wordInsertEdittext?.text.toString()
        if ( word == "" ) { Toast.makeText(context,"단어 또는 뜻이 없어 추가할 수 없습니다.",Toast.LENGTH_SHORT).show() }
        else {
            for (i in binding?.wordMeanInsert?.children!!) {
                val meanId =
                    i.findViewById<RelativeLayout>(i.id)
                        ?.findViewById<EditText>(R.id.insert_mean_edittext)
                val partId =
                    i.findViewById<RelativeLayout>(i.id)
                        ?.findViewById<Spinner>(R.id.insert_form_spinner)
                val mean = meanId?.text.toString()
                val part = partId?.selectedItem.toString()
                val wordEntity = WordEntity(0, word, part, mean)
                if (mean != "" && word != "") {
                    wordList.add(wordEntity)
                } else {
                    Toast.makeText(context, "단어 또는 뜻이 없어 추가할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            if ( !wordList.isEmpty() ) {
                insertList(wordList)
            }
            else
            {
                Toast.makeText(context, "단어 또는 뜻이 없어 추가할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertList(wordEntity: ArrayList<WordEntity>)
    {
        for ( i in wordEntity.indices)
        {
            viewModel.onEvent(WordEvent.Insert(wordEntity[i]))
        }
        lifecycleScope.launch {
            Toast.makeText(context,wordEntity[0].word + " 등록 완료.",Toast.LENGTH_LONG).show()
            wordList.clear()
        }
    }

    private fun insertByDic()
    {

    }

}