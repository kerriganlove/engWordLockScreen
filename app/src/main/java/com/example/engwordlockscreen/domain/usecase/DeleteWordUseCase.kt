package com.example.engwordlockscreen.domain.usecase

import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository

class DeleteWordUseCase(
    private val repository: WordRepository
)
{
    suspend operator fun invoke(wordEntity : WordEntity)
    {
        repository.insertWord(wordEntity)
    }
}