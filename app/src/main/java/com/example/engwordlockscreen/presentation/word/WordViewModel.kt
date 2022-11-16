package com.example.engwordlockscreen.presentation.word

import androidx.lifecycle.*
import com.example.engwordlockscreen.data.datasource.database.dto.WordEntity
import com.example.engwordlockscreen.domain.usecase.wordusecases.WordUseCases
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

    suspend fun viewList() : StateFlow<MutableList<WordEntity>> = wordUseCases.viewListUseCase().stateIn(viewModelScope)

    suspend fun viewSameWord(s : String) : StateFlow<MutableList<WordEntity>> = wordUseCases.sameWordUseCase(s).stateIn(viewModelScope)
}