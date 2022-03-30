package com.example.engwordlockscreen.domain.usecase

import androidx.lifecycle.LiveData
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository

class SameWordUseCase(
    private val repository: WordRepository
)
{
    suspend operator fun invoke(s : String) : LiveData<MutableList<WordEntity>>
    {
        return repository.viewSameWord(s)
    }
}