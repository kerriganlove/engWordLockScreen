package com.example.engwordlockscreen.presentation.lockscreen.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engwordlockscreen.domain.usecase.quizusecases.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
) : ViewModel()
{
    private val _times = MutableStateFlow<String>("")
    var times = _times.asStateFlow()

    init {

    }

    suspend fun getMultiChoiceList() = quizUseCases.multiChoiceUseCase().map { wordList ->
        wordList.shuffled()
            .take(9)

    }.stateIn(viewModelScope)

    suspend fun getPuzzleList() = quizUseCases.puzzleUseCases().map { wordList ->
        wordList.groupBy { it.word }
            .values.shuffled()
            .map{ it.random() }
            .take(5)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

}