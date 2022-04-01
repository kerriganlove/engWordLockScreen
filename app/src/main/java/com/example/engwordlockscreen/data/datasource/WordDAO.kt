package com.example.engwordlockscreen.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.example.engwordlockscreen.domain.database.WordEntity

@Dao
interface WordDAO
{
    @Insert(onConflict = ABORT)
    suspend fun insertWordDB(wordEntity : WordEntity)

    @Update
    suspend fun updateCurMean(wordEntity : WordEntity)

    @Query("SELECT * FROM wordDB")
    suspend fun getList() : List<WordEntity>

    @Query("SELECT :word FROM wordDB")
    suspend fun wordCheck (word : String) : Boolean

    @Query("SELECT * FROM wordDB GROUP BY word ORDER BY id")
    fun viewList() : LiveData<MutableList<WordEntity>>

    @Query("SELECT * FROM wordDB WHERE word = :s")
    fun viewSameWord(s : String) : LiveData<MutableList<WordEntity>>

    @Query("DELETE FROM wordDB WHERE word = :s")
    suspend fun deleteSameWords(s : String)

    @Query("DELETE FROM wordDB")
    suspend fun deleteAllWords()

}