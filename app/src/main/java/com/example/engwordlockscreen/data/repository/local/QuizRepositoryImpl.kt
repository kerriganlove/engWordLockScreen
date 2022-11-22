package com.example.engwordlockscreen.data.repository.local

import com.example.engwordlockscreen.data.datasource.database.WordDAO
import com.example.engwordlockscreen.data.datasource.database.dto.toWordEntities
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao : WordDAO
) : QuizRepository{
    override suspend fun getMultiChoiceWord() : Flow<MutableList<WordEntities>> = flow {
        val wordEntities = dao.getListByMultiChoiceQuiz().map { it.toWordEntities() }.toMutableList()
        emit(wordEntities)
    }

    override suspend fun getPuzzleWord() : Flow<MutableList<WordEntities>> = flow {
        val wordEntities = dao.getListByPuzzleQuiz().map { it.toWordEntities() }.toMutableList()
        emit(wordEntities)
    }

}