package com.example.engwordlockscreen.domain.repository

import androidx.lifecycle.LiveData
import com.example.engwordlockscreen.domain.database.WordEntity

interface WordRepository {

    suspend fun insertWord(wordEntity: WordEntity)

    suspend fun viewList() : LiveData<MutableList<WordEntity>>

    suspend fun viewSameWord(s : String) : LiveData<MutableList<WordEntity>>

    suspend fun deleteSameWords(s : String)
}