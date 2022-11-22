package com.example.engwordlockscreen.presentation.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.usecase.quizusecases.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
) : ViewModel()
{
    suspend fun getMultiChoiceList() : StateFlow<List<WordEntities>> = quizUseCases.multiChoiceUseCase().stateIn(viewModelScope)
    suspend fun getPuzzleList() : StateFlow<List<WordEntities>> = quizUseCases.puzzleUseCases().stateIn(viewModelScope)
}