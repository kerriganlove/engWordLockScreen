package com.example.engwordlockscreen.domain.repository

import com.example.engwordlockscreen.domain.remote.WordApiModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun getWordListByApi(engWord : String) : Flow<List<WordApiModel>>
}