package com.example.engwordlockscreen.domain.usecase.wordusecases

import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class SearchWordUseCase(
    private val repository: WordRepository
) {
    suspend operator fun invoke(s : String) : Flow<List<WordEntities>> {
        return repository.searchWord(s)
    }
}