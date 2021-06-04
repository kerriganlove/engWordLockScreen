package com.example.engwordlockscreen.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wordDB")
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id  : Int,
    var word : String,
    var parts: String,
    var mean : String) {

}