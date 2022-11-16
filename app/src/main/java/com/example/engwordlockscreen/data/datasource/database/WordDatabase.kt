package com.example.engwordlockscreen.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity

@Database(entities = [WordEntity::class], version = 1)
abstract class WordDatabase : RoomDatabase()
{
    abstract fun wordDAO() : WordDAO

    companion object {
        const val DATABASE_NAME = "wordDB.db"
    }
}