package com.example.engwordlockscreen.domain.usecase

import androidx.lifecycle.LiveData
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository

class ViewListUseCase(
    private val repository: WordRepository
)
{
    suspend operator fun invoke() : LiveData<MutableList<WordEntity>>
    {
        return repository.viewList()
    }
}