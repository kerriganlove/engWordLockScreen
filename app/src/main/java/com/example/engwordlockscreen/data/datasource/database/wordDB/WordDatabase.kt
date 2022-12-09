package com.example.engwordlockscreen.data.datasource.database.wordDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.engwordlockscreen.constants.CustomConst
import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity

@Database(entities = [WordEntity::class], version = CustomConst.APP_VERSION_CODE)
abstract class WordDatabase : RoomDatabase()
{
    abstract fun wordDAO() : WordDAO

    companion object {
        const val DATABASE_NAME = "wordDB.db"
    }
}