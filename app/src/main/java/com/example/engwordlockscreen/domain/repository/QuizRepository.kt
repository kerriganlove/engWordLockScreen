package com.example.engwordlockscreen.domain.repository

import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getMultiChoiceWord() : Flow<MutableList<WordEntity>>

    suspend fun getPuzzleWord() : Flow<MutableList<WordEntity>>
}