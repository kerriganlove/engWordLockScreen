package com.example.engwordlockscreen.data.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.engwordlockscreen.domain.database.WordEntity

@Database(entities = [WordEntity::class], version = 1)
abstract class WordDatabase : RoomDatabase()
{
    abstract fun wordDAO() : WordDAO

    companion object {
        var Instance : WordDatabase? = null

        fun getInstance(context : Context) : WordDatabase? {
            if ( Instance == null )
            {
                synchronized(WordDatabase::class){
                    Instance = Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "wordDB.db")
                        .build()
                }
            }
            return Instance
        }
    }
}