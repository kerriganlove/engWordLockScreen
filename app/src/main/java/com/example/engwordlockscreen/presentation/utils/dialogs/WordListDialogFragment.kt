package com.example.engwordlockscreen.presentation.utils.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engwordlockscreen.R
import com.example.engwordlockscreen.databinding.CustomWordDialogBinding
import com.example.engwordlockscreen.domain.database.WordEntities

class WordListDialogFragment(
    private val wordList : List<WordEntities>
) : DialogFragment() {

    private var _binding : CustomWordDialogBinding? = null
    private val binding get() = _binding!!

    /*
     * Local Value
     */
    private val defaultWidth by lazy { WindowManager.LayoutParams.MATCH_PARENT }
    private val defaultHeight by lazy { if (wordList.size >= 10) {resources.getDimensionPixelSize(R.dimen.word_list_dialog_height)} else { WindowManager.LayoutParams.WRAP_CONTENT } }

    /*
     * LifeCycle Callback
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = CustomWordDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            meanRecyclerview.apply {
                adapter = WordListDialogRecyclerViewAdapter(wordList)
                layoutManager = LinearLayoutManager(context)
            }
            dialogMainWord.apply {
                text = wordList[0].word
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        dialog?.window?.setLayout(defaultWidth, defaultHeight)
        super.onResume()
    }

    /*
     * Function
     */

}