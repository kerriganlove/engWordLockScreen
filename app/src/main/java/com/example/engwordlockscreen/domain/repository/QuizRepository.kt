package com.example.engwordlockscreen.domain.repository

import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import com.example.engwordlockscreen.domain.database.WordEntities
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    suspend fun getMultiChoiceWord() : Flow<MutableList<WordEntities>>

    suspend fun getPuzzleWord() : Flow<MutableList<WordEntities>>
}