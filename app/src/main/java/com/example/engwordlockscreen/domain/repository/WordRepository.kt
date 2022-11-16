package com.example.engwordlockscreen.domain.repository

import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    suspend fun insertWord(wordEntity: WordEntity)

    suspend fun viewList() : Flow<MutableList<WordEntity>>

    suspend fun viewSameWord(s : String) : Flow<MutableList<WordEntity>>

    suspend fun deleteSameWords(s : String)

    suspend fun deleteAllWords()
}