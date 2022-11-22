package com.example.engwordlockscreen.data.repository.remote

import com.example.engwordlockscreen.constants.Response
import com.example.engwordlockscreen.domain.remote.WordApiModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun getWordListByApi(engWord : String) : Flow<Response<List<WordApiModel>>>
}