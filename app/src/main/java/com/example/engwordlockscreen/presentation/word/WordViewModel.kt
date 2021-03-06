package com.example.engwordlockscreen.presentation.word

import android.util.Log
import androidx.lifecycle.*
import com.example.engwordlockscreen.domain.database.WordEntity
import com.example.engwordlockscreen.domain.repository.WordRepository
import com.example.engwordlockscreen.domain.usecase.WordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
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
            is WordEvent.SameWord -> {
                viewModelScope.launch {
                    wordUseCases.sameWordUseCase(event.s)
                }
            }
        }
    }

    fun viewList() = liveData {
        emit(wordUseCases.viewListUseCase().value)
    }
    fun deleteAllList()
    {
        viewModelScope.launch {
            wordUseCases.deleteAllWordUseCase()
        }
    }
}