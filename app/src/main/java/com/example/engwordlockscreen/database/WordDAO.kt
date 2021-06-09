package com.example.engwordlockscreen.database

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface WordDAO
{
    @Insert(onConflict = ABORT)
    fun insertWordDB(wordEntity : WordEntity)

    @Update
    fun updateCurMean(wordEntity : WordEntity)

    @Query("SELECT * FROM wordDB")
    fun getList() : List<WordEntity>

    @Query("SELECT :word FROM wordDB")
    fun wordCheck (word : String) : Boolean

    @Query("SELECT * FROM wordDB ORDER BY word")
    fun viewList() : MutableList<WordEntity>
}