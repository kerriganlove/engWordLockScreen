package com.example.engwordlockscreen.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDAO
{
    @Insert(onConflict = REPLACE)
    fun insertWordDB(wordEntity : WordEntity)

    @Update
    fun updateCurMean(wordEntity : WordEntity)

    @Query("SELECT * FROM wordDB")
    fun getList() : List<WordEntity>

    @Query("SELECT :word FROM wordDB")
    fun wordCheck (word : String) : Boolean


}