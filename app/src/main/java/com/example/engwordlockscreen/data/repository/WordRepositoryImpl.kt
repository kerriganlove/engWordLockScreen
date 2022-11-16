package com.example.engwordlockscreen.data.repository

import com.example.engwordlockscreen.data.datasource.database.WordDAO
import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
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