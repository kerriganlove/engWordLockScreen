package com.example.engwordlockscreen.di

import android.app.Application
import androidx.room.Room
import com.example.engwordlockscreen.data.datasource.database.wordDB.WordDatabase
import com.example.engwordlockscreen.data.datasource.database.wordDB.WordDatabase.Companion.DATABASE_NAME
import com.example.engwordlockscreen.data.datasource.remote.ApiService
import com.example.engwordlockscreen.data.repository.WordRepositoryImpl
import com.example.engwordlockscreen.data.repository.remote.ApiRepositoryImpl
import com.example.engwordlockscreen.data.repository.local.QuizRepositoryImpl
import com.example.engwordlockscreen.data.repository.local.LocalWordRepositoryImpl
import com.example.engwordlockscreen.data.repository.remote.ApiRepository
import com.example.engwordlockscreen.data.repository.local.LocalWordRepository
import com.example.engwordlockscreen.domain.repository.QuizRepository
import com.example.engwordlockscreen.domain.repository.WordRepository
import com.example.engwordlockscreen.domain.usecase.quizusecases.*
import com.example.engwordlockscreen.domain.usecase.wordusecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private lateinit var instance : WordDatabase

    /*
     * Default Coroutine Scope
     */
    @Provides
    @Singleton
    fun provideCoroutineScope() : CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    /*
     * Data Layer
     * Data Source(Local, Network)
     */
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
    fun provideWordApiService() : ApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.naver.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    /*
     * Data Layer
     * Repository
     */
    @Provides
    @Singleton
    fun provideQuizRepository(db : WordDatabase) : QuizRepository {
        return QuizRepositoryImpl(db.wordDAO())
    }
    @Provides
    @Singleton
    fun provideLocalWordRepository(db : WordDatabase) : LocalWordRepository
    {
        return LocalWordRepositoryImpl(db.wordDAO())
    }

    @Provides
    @Singleton
    fun provideWordApiRepository(network : ApiService) : ApiRepository {
        return ApiRepositoryImpl(network)
    }

    @Provides
    @Singleton
    fun provideWordRepository(localRepository : LocalWordRepository, remoteRepository: ApiRepository) : WordRepository {
        return WordRepositoryImpl(localRepository, remoteRepository)
    }

    /*
     * Domain Layer
     * Use cases
     */
    @Provides
    @Singleton
    fun provideWordUseCases(repository : WordRepository) : WordUseCases
    {
        return WordUseCases(
            deleteWordUseCase = DeleteWordUseCase(repository),
            insertWordUseCase = InsertWordUseCase(repository),
            sameWordUseCase = SameWordUseCase(repository),
            viewListUseCase = ViewListUseCase(repository),
            deleteAllWordUseCase = DeleteAllWordUseCase(repository),
            viewListByNaverApi = ViewListByNaverApiUseCase(repository),
            searchWordUseCase = SearchWordUseCase(repository)
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