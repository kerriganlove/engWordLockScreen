package com.example.engwordlockscreen.domain.repository

import com.example.engwordlockscreen.domain.database.WordEntities
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getMultiChoiceWord() : Flow<List<WordEntities>>

    suspend fun getPuzzleWord() : Flow<List<WordEntities>>
}