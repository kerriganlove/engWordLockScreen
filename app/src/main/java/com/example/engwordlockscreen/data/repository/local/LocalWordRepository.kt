package com.example.engwordlockscreen.data.repository.local

import com.example.engwordlockscreen.domain.database.WordEntities
import kotlinx.coroutines.flow.Flow

interface LocalWordRepository {
    suspend fun insertWord(wordEntity: WordEntities)

    suspend fun viewList() : Flow<MutableList<WordEntities>>

    suspend fun viewSameWord(s : String) : Flow<MutableList<WordEntities>>

    suspend fun deleteSameWords(s : String)

    suspend fun deleteAllWords()
}