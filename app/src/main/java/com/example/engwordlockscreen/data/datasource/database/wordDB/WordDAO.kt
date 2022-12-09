package com.example.engwordlockscreen.data.datasource.database.wordDB

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

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM wordDB")
    fun getListByMultiChoiceQuiz() : Flow<List<WordEntity>>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT *, (SELECT mean FROM wordDB ORDER BY RANDOM() LIMIT 1) FROM wordDB PI GROUP BY word LIMIT 5")
    fun getListByPuzzleQuiz(): Flow<List<WordEntity>>

}