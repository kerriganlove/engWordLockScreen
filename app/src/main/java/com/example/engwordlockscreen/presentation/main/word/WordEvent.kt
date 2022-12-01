package com.example.engwordlockscreen.presentation.main.word

import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import com.example.engwordlockscreen.domain.database.WordEntities

sealed class WordEvent
{
    data class Insert(val w : WordEntities) : WordEvent()
    data class Delete(val s : String) : WordEvent()
    data class InsertByApi(val w : WordEntities) : WordEvent()
    object ViewList : WordEvent()
    object DeleteAll : WordEvent()
}