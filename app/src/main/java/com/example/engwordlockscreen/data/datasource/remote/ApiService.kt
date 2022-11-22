package com.example.engwordlockscreen.data.datasource.remote

import com.example.engwordlockscreen.constants.CustomConst
import com.example.engwordlockscreen.data.datasource.remote.dto.ApiModel
import retrofit2.http.GET

interface ApiService {
    @GET(CustomConst.GET_LIST_BY_NAVER)
    fun getWordDescriptionList(query : String, display : Int = 5, start : Int = 1) : List<ApiModel>
}