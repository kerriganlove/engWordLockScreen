package com.example.engwordlockscreen.domain.usecase.wordusecases

import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class SameWordUseCase(
    private val repository: WordRepository
)
{
    suspend operator fun invoke(s : String) : Flow<MutableList<WordEntity>>
    {
        return repository.viewSameWord(s)
    }
}