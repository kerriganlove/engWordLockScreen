package com.example.engwordlockscreen.presentation.word

import androidx.lifecycle.LiveData
import com.example.engwordlockscreen.domain.database.WordEntity

sealed class WordEvent
{
    data class Insert(val w : WordEntity) : WordEvent()
    data class Delete(val s : String) : WordEvent()
    object DeleteAll : WordEvent()
}