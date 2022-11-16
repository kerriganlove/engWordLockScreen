package com.example.engwordlockscreen.domain.usecase.quizusecases

import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import com.example.engwordlockscreen.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class PuzzleUseCases(
    private val repository : QuizRepository
) {
    suspend operator fun invoke() : Flow<MutableList<WordEntity>> {
        return repository.getPuzzleWord()
    }
}