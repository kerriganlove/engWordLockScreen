package com.example.engwordlockscreen.domain.usecase

import com.example.engwordlockscreen.domain.repository.WordRepository

class DeleteAllWordUseCase(
    private val repository: WordRepository)
{
    suspend operator fun invoke()
    {
        repository.deleteAllWords()
    }
}