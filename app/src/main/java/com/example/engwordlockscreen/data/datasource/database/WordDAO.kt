package com.example.engwordlockscreen.data.datasource.database

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import kotlinx.coroutines.flow.Flow

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
    fun viewList() : Flow<List<WordEntity>>

    @Query("SELECT * FROM wordDB WHERE word = :s")
    fun viewSameWord(s : String) : Flow<List<WordEntity>>

    @Query("DELETE FROM wordDB WHERE word = :s")
    suspend fun deleteSameWords(s : String)

    @Query("DELETE FROM wordDB")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM wordDB GROUP BY word ORDER BY RANDOM() LIMIT 9")
    fun getListByMultiChoiceQuiz() : Flow<List<WordEntity>>

    @Query("SELECT * FROM wordDB GROUP BY word ORDER BY RANDOM() LIMIT 5")
    fun getListByPuzzleQuiz(): Flow<List<WordEntity>>

}