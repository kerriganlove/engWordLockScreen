package com.example.engwordlockscreen.domain.repository

import androidx.lifecycle.LiveData
import com.example.engwordlockscreen.domain.database.WordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface WordRepository {

    suspend fun insertWord(wordEntity: WordEntity)

    suspend fun viewList() : Flow<MutableList<WordEntity>>

    suspend fun viewSameWord(s : String) : Flow<MutableList<WordEntity>>

    suspend fun deleteSameWords(s : String)

    suspend fun deleteAllWords()
}