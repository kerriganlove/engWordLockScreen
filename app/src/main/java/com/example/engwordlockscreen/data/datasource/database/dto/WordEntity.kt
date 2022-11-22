package com.example.engwordlockscreen.data.datasource.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.engwordlockscreen.domain.database.WordEntities

@Entity(tableName = "wordDB")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id  : Int,
    var word : String,
    var parts: String,
    var mean : String)

fun WordEntity.toWordEntities() : WordEntities {
    return WordEntities(
        id = id,
        word = word,
        parts = parts,
        mean = mean
        )
}