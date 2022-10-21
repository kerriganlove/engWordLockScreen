package com.example.engwordlockscreen.data.repository

import androidx.lifecycle.LiveData
import com.example.engwordlockscreen.data.datasource.WordDAO
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class WordRepositoryImpl(
    private val dao : WordDAO
    ) : WordRepository {

    override suspend fun insertWord(wordEntity: WordEntity) {
        dao.insertWordDB(wordEntity)
    }

    override suspend fun viewList(): Flow<MutableList<WordEntity>> {
        return dao.viewList()
    }

    override suspend fun viewSameWord(s: String): Flow<MutableList<WordEntity>> {
        return dao.viewSameWord(s)
    }

    override suspend fun deleteSameWords(s: String) {
        return dao.deleteSameWords(s)
    }

    override suspend fun deleteAllWords() {
        return dao.deleteAllWords()
    }

}