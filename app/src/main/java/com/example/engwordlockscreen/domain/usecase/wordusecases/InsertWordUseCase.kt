package com.example.engwordlockscreen.domain.usecase.wordusecases

import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository

class InsertWordUseCase (
    private val repository: WordRepository)
{

    suspend operator fun invoke(word : WordEntity)
    {
        repository.insertWord(word)
    }
}