package com.example.engwordlockscreen.data.repository.local

import com.example.engwordlockscreen.data.datasource.database.wordDB.WordDAO
import com.example.engwordlockscreen.data.datasource.database.dto.toWordEntities
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao : WordDAO
) : QuizRepository{
    override suspend fun getMultiChoiceWord() : Flow<List<WordEntities>> {
        return dao.getListByMultiChoiceQuiz().map { it -> it.map { it.toWordEntities()} }
    }

    override suspend fun getPuzzleWord() : Flow<List<WordEntities>> {
        return dao.getListByPuzzleQuiz().map { it -> it.map { it.toWordEntities()}}
    }

}