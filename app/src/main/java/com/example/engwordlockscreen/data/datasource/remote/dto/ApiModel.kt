package com.example.engwordlockscreen.data.datasource.remote.dto

import com.example.engwordlockscreen.domain.remote.WordApiModel
import com.google.gson.annotations.SerializedName

data class ApiModel(
    val title : String,
    val link : String,
    @SerializedName("description")
    val desc : String,
    val thumbnail : String
)

fun ApiModel.toWordApiModel() : WordApiModel {
    return WordApiModel(
        title = title,
        link = link,
        desc = desc,
        thumbnail = thumbnail
    )
}