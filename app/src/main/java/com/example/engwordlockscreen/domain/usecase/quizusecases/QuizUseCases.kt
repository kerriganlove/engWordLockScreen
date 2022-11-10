package com.example.engwordlockscreen.domain.usecase.quizusecases

import com.example.engwordlockscreen.domain.usecase.wordusecases.*

data class QuizUseCases(
    val multiChoiceUseCase: MultiChoiceUseCase,
    val puzzleUseCases: PuzzleUseCases
)
