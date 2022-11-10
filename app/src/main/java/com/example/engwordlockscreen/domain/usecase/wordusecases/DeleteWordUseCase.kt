package com.example.engwordlockscreen.domain.usecase.wordusecases

import com.example.engwordlockscreen.domain.repository.WordRepository

class DeleteWordUseCase(
    private val repository: WordRepository
)
{
    suspend operator fun invoke(s: String)
    {
        repository.deleteSameWords(s)
    }
}