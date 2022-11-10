package com.example.engwordlockscreen.domain.usecase.quizusecases

import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class MultiChoiceUseCase(
    private val repository: QuizRepository
) {
    suspend operator fun invoke() : Flow<MutableList<WordEntity>> {
        return repository.getMultiChoiceWord()
    }
}