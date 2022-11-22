package com.example.engwordlockscreen.data.repository.remote

import com.example.engwordlockscreen.constants.CustomConst
import com.example.engwordlockscreen.constants.Response
import com.example.engwordlockscreen.data.datasource.remote.ApiService
import com.example.engwordlockscreen.data.datasource.remote.dto.toWordApiModel
import com.example.engwordlockscreen.domain.remote.WordApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    //TODO Error Handling
    override suspend fun getWordListByApi(engWord : String): Flow<Response<List<WordApiModel>>> = flow {
        try {
            val words = apiService.getWordDescriptionList(engWord).map {
                it.toWordApiModel()
            }
            emit(Response.Success(words))
        }
        catch (e : HttpException) {
            emit(Response.Error(e.localizedMessage ?: CustomConst.ERROR_BY_UNEXPECTED))
        }
        catch (e : IOException) {
            emit(Response.Error(CustomConst.ERROR_BY_NETWORK))
        }
    }.flowOn(Dispatchers.IO)

}