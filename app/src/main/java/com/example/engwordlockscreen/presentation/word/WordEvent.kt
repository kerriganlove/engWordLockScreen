package com.example.engwordlockscreen.presentation.word

import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity

sealed class WordEvent
{
    data class Insert(val w : WordEntity) : WordEvent()
    data class Delete(val s : String) : WordEvent()
    object DeleteAll : WordEvent()
}