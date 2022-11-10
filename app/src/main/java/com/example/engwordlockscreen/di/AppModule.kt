package com.example.engwordlockscreen.di

import android.app.Application
import androidx.room.Room
import com.example.engwordlockscreen.data.datasource.WordDatabase
import com.example.engwordlockscreen.data.datasource.WordDatabase.Companion.DATABASE_NAME
import com.example.engwordlockscreen.data.repository.QuizRepositoryImpl
import com.example.engwordlockscreen.data.repository.WordRepositoryImpl
import com.example.engwordlockscreen.domain.repository.QuizRepository
import com.example.engwordlockscreen.domain.repository.WordRepository
import com.example.engwordlockscreen.domain.usecase.quizusecases.*
import com.example.engwordlockscreen.domain.usecase.wordusecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private lateinit var instance : WordDatabase
    @Provides
    @Singleton
    fun provideWordDatabase(app : Application) : WordDatabase
    {
        synchronized(WordDatabase::class){
            instance = Room.databaseBuilder(app,
                WordDatabase::class.java,
                DATABASE_NAME)
                .build()
        }
        return instance
    }
    @Provides
    @Singleton
    fun provideQuizRepository(db : WordDatabase) : QuizRepository {
        return QuizRepositoryImpl(db.wordDAO())
    }
    @Provides
    @Singleton
    fun provideWordRepository(db : WordDatabase) : WordRepository
    {
        return WordRepositoryImpl(db.wordDAO())
    }
    @Provides
    @Singleton
    fun provideWordUseCases(repository : WordRepository) : WordUseCases
    {
        return WordUseCases(
            deleteWordUseCase = DeleteWordUseCase(repository),
            insertWordUseCase = InsertWordUseCase(repository),
            sameWordUseCase = SameWordUseCase(repository),
            viewListUseCase = ViewListUseCase(repository),
            deleteAllWordUseCase = DeleteAllWordUseCase(repository)
                           )
    }
    @Provides
    @Singleton
    fun provideQuizUseCases(repository : QuizRepository) : QuizUseCases {
        return QuizUseCases(
            multiChoiceUseCase = MultiChoiceUseCase(repository),
            puzzleUseCases = PuzzleUseCases(repository)
        )
    }
}