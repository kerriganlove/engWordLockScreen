package com.example.engwordlockscreen.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT

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

    @Query("SELECT * FROM wordDB GROUP BY word ORDER BY id")
    fun viewList() : LiveData<MutableList<WordEntity>>

    @Query("SELECT * FROM wordDB WHERE word = :s")
    fun viewSameWord(s : String) : LiveData<MutableList<WordEntity>>

    @Query("DELETE FROM wordDB WHERE word = :s")
    fun deleteSameWords(s : String)
}