package com.example.engwordlockscreen.data.repository

import com.example.engwordlockscreen.data.datasource.WordDAO
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class QuizRepositoryImpl(
    private val dao : WordDAO
) : QuizRepository{
    override suspend fun getMultiChoiceWord() : Flow<MutableList<WordEntity>> {
        return dao.getListByMultiChoiceQuiz()
    }

    override suspend fun getPuzzleWord() : Flow<MutableList<WordEntity>> {
        return dao.getListByPuzzleQuiz()
    }

}