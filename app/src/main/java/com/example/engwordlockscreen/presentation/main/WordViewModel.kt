package com.example.engwordlockscreen.presentation.main

import androidx.lifecycle.*
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

    suspend fun viewList() : StateFlow<List<WordEntities>> = wordUseCases.viewListUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    suspend fun viewSameWord(s : String) : StateFlow<List<WordEntities>> = wordUseCases.sameWordUseCase(s)
        .stateIn(viewModelScope,SharingStarted.WhileSubscribed(), emptyList())
}