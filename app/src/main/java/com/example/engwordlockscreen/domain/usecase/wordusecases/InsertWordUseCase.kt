package com.example.engwordlockscreen.domain.usecase.wordusecases

import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.repository.WordRepository

class InsertWordUseCase (
    private val repository: WordRepository)
{

    suspend operator fun invoke(word : WordEntities)
    {
        repository.insertWord(word)
    }
}