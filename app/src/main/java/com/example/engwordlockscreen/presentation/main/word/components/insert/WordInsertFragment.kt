package com.example.engwordlockscreen.presentation.main.word.components.insert

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.constants.CustomConst
import com.example.engwordlockscreen.databinding.FragmentWordInsertBinding
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.presentation.main.WordViewModel
import com.example.engwordlockscreen.presentation.main.word.WordEvent
import com.example.engwordlockscreen.presentation.utils.showAddViewOverflowToast
import com.example.engwordlockscreen.presentation.utils.showInsertCompleteToast
import com.example.engwordlockscreen.presentation.utils.showInsertErrorToast
import com.example.engwordlockscreen.presentation.utils.string.StringFilter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WordInsertFragment : Fragment() {
    private var _binding : FragmentWordInsertBinding? = null
    private val binding get() = _binding!!
    private var wordList = arrayListOf<WordEntities>()
    private val viewModel by activityViewModels<WordViewModel>()


    /*
     *  LifeCycle Callback
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        Log.d("onCreate", "onCreate() Call")
        _binding = FragmentWordInsertBinding.inflate(inflater,container,false)
        init()
        buttonClick()
        return binding.root
    }

    override fun onPause() {
        Log.d("onPause", "onPause() Call")
        super.onPause()
    }

    override fun onStop() {
        Log.d("onStop", "onStop() Call")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("onDestroy","onDestroyView() Call")
        super.onDestroyView()
        _binding = null
    }


    /*
     *  Function for Views
     */

    private fun init()
    {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.wordInsertEdittext.filters = arrayOf(StringFilter.filterENG)
        binding.wordMeanInsert.apply {
            adapter = WordInsertRecyclerViewAdapter { pos, wordEntities ->
                onChange(pos, wordEntities)
            }
            animation = null
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun buttonClick()
    {
        binding.insertFormButton.setOnClickListener {
            insertForm()
        }
        binding.deleteFormButton.setOnClickListener {
            removeForm()
        }
        binding.insertList.setOnClickListener {
            insertDB()
        }
    }

    /*
     *  Recycler View Item Change
     */
    private fun onChange(pos: Int, data: WordEntities) {
        Log.d("Hi","$pos, $data")
        val list = viewModel._insertWordList.value.toMutableList()
        list[pos] = data
        viewModel._insertWordList.value = list.toList()
    }

    // TODO RecyclerView 형태로 변화.
    private fun insertForm() {
       viewModel._insertWordList.value.toMutableList().run {
            if ( size < 5 ) {
                add(WordEntities())
                viewModel._insertWordList.value = this.toList()
                binding.wordMeanInsert.adapter?.notifyItemInserted(size-1)
            } else {
                context?.showAddViewOverflowToast()
            }
        }
    }

    // TODO RecyclerView 형태로 변화.
    private fun removeForm() {
        viewModel._insertWordList.value.toMutableList().run {
            if ( size > 0) {
                removeLast()
                viewModel._insertWordList.value = this.toList()
                binding.wordMeanInsert.adapter?.notifyItemRemoved(size)
            }
        }
    }

    private fun insertDB()
    {
        val word = binding.wordInsertEdittext.text.toString()
        if ( word == "" ) { Toast.makeText(context,"단어 또는 뜻이 없어 추가할 수 없습니다.",Toast.LENGTH_SHORT).show() }
        else {
            for (i in binding.wordMeanInsert.children!!) {
                val meanId =
                    i.findViewById<RelativeLayout>(i.id)
                        ?.findViewById<EditText>(R.id.insert_mean_edittext)
                val partId =
                    i.findViewById<RelativeLayout>(i.id)
                        ?.findViewById<Spinner>(R.id.insert_form_spinner)
                val mean = meanId?.text.toString()
                val part = partId?.selectedItem.toString()
                val wordEntity = WordEntities(0, word, part, mean)
                if (mean != "" && word != "") {
                    wordList.add(wordEntity)
                } else {
                    Toast.makeText(context, "단어 또는 뜻이 없어 추가할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            if (wordList.isNotEmpty()) {
                insertList(wordList)
            }
            else
            {
                Toast.makeText(context, "단어 또는 뜻이 없어 추가할 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertList(wordEntities: ArrayList<WordEntities>)
    {
        viewLifecycleOwner.lifecycleScope.launch {
            runCatching {
                wordEntities.forEach { wordEntity ->
                    viewModel.onEvent(WordEvent.Insert(wordEntity))
                }
            }.onSuccess {
                requireContext().showInsertCompleteToast(wordEntities[0].word)
            }.onFailure {
                requireContext().showInsertErrorToast(wordEntities[0].word)
            }
            wordList.clear()
        }
    }

    private fun insertByDic() {

    }

}