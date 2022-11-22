package com.example.engwordlockscreen.data.repository.local

import com.example.engwordlockscreen.data.datasource.database.WordDAO
import com.example.engwordlockscreen.data.datasource.database.dto.toWordEntities
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.database.toWordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalWordRepositoryImpl @Inject constructor(
    private val dao : WordDAO,
    ) : LocalWordRepository {

    override suspend fun insertWord(wordEntity: WordEntities) {
        dao.insertWordDB(wordEntity.toWordEntity())
    }

    override suspend fun viewList(): Flow<MutableList<WordEntities>> = flow {
        emit(dao.getList().map { it.toWordEntities() }.toMutableList())
    }

    override suspend fun viewSameWord(s: String): Flow<MutableList<WordEntities>> = flow {
        emit(dao.getList().map { it.toWordEntities() }.toMutableList())
    }

    override suspend fun deleteSameWords(s: String) {
        return dao.deleteSameWords(s)
    }

    override suspend fun deleteAllWords() {
        return dao.deleteAllWords()
    }

}