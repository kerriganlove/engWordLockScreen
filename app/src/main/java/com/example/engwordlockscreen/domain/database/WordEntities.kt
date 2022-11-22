package com.example.engwordlockscreen.domain.database

import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity

data class WordEntities(
    val id  : Int,
    var word : String,
    var parts: String,
    var mean : String
)

fun WordEntities.toWordEntity() : WordEntity {
    return WordEntity(
        id = id,
        word = word,
        parts = parts,
        mean = mean
    )

}
