package com.example.engwordlockscreen.presentation.main

import android.util.Log
import androidx.lifecycle.*
import com.example.engwordlockscreen.constants.CustomConst
import com.example.engwordlockscreen.constants.UiState
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.usecase.wordusecases.WordUseCases
import com.example.engwordlockscreen.presentation.main.word.WordEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val wordUseCases: WordUseCases,
    ) : ViewModel() {

    var _insertWordList = MutableStateFlow<List<WordEntities>>(mutableListOf())
    val insertWordList = _insertWordList.asStateFlow()

    var searchText = MutableStateFlow<String>("")

    private var _searchResult = MutableStateFlow<List<WordEntities>>(listOf())
    val searchResult = _searchResult.asStateFlow()

    fun showLog() {
        viewModelScope.launch {
            insertWordList.collect {
                Log.d("Hi","i'm changed ${it.size}")
            }
        }
    }

    var _word = MutableStateFlow("")
    val word = _word.asStateFlow()

    fun onEvent(event: WordEvent) {
        when (event) {
            is WordEvent.Insert -> {
                viewModelScope.launch {
                    wordUseCases.insertWordUseCase(event.w)
                }
            }
            is WordEvent.Delete -> {
                viewModelScope.launch {
                    wordUseCases.deleteWordUseCase(event.s)
                }
            }
            is WordEvent.DeleteAll -> {
                viewModelScope.launch {
                    wordUseCases.deleteAllWordUseCase()
                }
            }
        }
    }

    suspend fun viewList() = wordUseCases.viewListUseCase()
        .map {
            UiState.Success(suc_data = it)
        }.catch { e ->
            UiState.Fail(err_data = 0 , err_msg = "$e" )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Loading)

    suspend fun viewSameWord(s : String) : StateFlow<List<WordEntities>> = wordUseCases.sameWordUseCase(s)
        .stateIn(viewModelScope)
}