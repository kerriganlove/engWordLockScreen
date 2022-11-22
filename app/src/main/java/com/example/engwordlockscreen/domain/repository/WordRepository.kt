package com.example.engwordlockscreen.domain.repository

import com.example.engwordlockscreen.constants.Response
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.remote.WordApiModel
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    suspend fun insertWord(wordEntity: WordEntities)

    suspend fun viewList() : Flow<List<WordEntities>>

    suspend fun viewSameWord(s : String) : Flow<List<WordEntities>>

    suspend fun deleteSameWords(s : String)

    suspend fun deleteAllWords()

    suspend fun getWordListByApi(engWord : String) : Flow<Response<List<WordApiModel>>>
}