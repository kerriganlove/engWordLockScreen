package com.example.engwordlockscreen.data.repository

import com.example.engwordlockscreen.data.datasource.database.WordDAO
import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import com.example.engwordlockscreen.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao : WordDAO
) : QuizRepository{
    override suspend fun getMultiChoiceWord() : Flow<MutableList<WordEntity>> {
        return dao.getListByMultiChoiceQuiz()
    }

    override suspend fun getPuzzleWord() : Flow<MutableList<WordEntity>> {
        return dao.getListByPuzzleQuiz()
    }

}