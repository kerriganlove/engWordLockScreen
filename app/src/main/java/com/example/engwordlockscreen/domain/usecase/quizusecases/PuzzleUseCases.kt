package com.example.engwordlockscreen.domain.usecase.quizusecases

import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class PuzzleUseCases(
    private val repository : QuizRepository
) {
    suspend operator fun invoke() : Flow<List<WordEntities>> {
        return repository.getPuzzleWord()
    }
}