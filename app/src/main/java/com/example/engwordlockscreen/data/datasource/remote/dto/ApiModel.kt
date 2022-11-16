package com.example.engwordlockscreen.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class ApiModel(
    val title : String,
    val link : String,
    @SerializedName("description")
    val desc : String,
    val thumbnail : String
)
