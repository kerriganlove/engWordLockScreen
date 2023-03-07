package com.example.engwordlockscreen.data.repository

import com.example.engwordlockscreen.constants.Response
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.remote.WordApiModel
import com.example.engwordlockscreen.data.repository.remote.ApiRepository
import com.example.engwordlockscreen.data.repository.local.LocalWordRepository
import com.example.engwordlockscreen.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val localRepository : LocalWordRepository,
    private val remoteRepository : ApiRepository
) : WordRepository {
    override suspend fun insertWord(wordEntity: WordEntities) {
        localRepository.insertWord(wordEntity)
    }

    override suspend fun viewList(): Flow<List<WordEntities>> {
       return localRepository.viewList()
    }

    override suspend fun viewSameWord(s: String): Flow<List<WordEntities>> {
        return localRepository.viewSameWord(s)
    }

    override suspend fun deleteSameWords(s: String) {
        localRepository.deleteSameWords(s)
    }

    override suspend fun deleteAllWords() {
        localRepository.deleteAllWords()
    }

    override suspend fun getWordListByApi(engWord: String): Flow<Response<List<WordApiModel>>> {
        return remoteRepository.getWordListByApi(engWord)
    }

    override suspend fun searchWord(s: String): Flow<List<WordEntities>> {
        return localRepository.searchWord(s)
    }
}