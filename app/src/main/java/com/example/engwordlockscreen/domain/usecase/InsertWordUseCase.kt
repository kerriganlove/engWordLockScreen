package com.example.engwordlockscreen.domain.usecase

import com.example.engwordlockscreen.domain.repository.WordRepository

class InsertWordUseCase (
    private val repository: WordRepository)
{

    suspend operator fun invoke(s : String)
    {
        repository.deleteSameWords(s)
    }
}