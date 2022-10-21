package com.example.engwordlockscreen.domain.usecase

import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class ViewListUseCase(
    private val repository: WordRepository
)
{
    suspend operator fun invoke() : Flow<MutableList<WordEntity>>
    {
        return repository.viewList()
    }
}