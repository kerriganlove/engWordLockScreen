package com.example.engwordlockscreen.data.repository

import com.example.engwordlockscreen.data.datasource.remote.ApiService
import com.example.engwordlockscreen.domain.remote.WordApiModel
import com.example.engwordlockscreen.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {
    override suspend fun getWordListByApi(engWord : String): List<WordApiModel> {
        return apiService.getWordDescriptionList(engWord)
    }


}