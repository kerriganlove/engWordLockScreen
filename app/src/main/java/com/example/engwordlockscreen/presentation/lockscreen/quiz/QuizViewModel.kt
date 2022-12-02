package com.example.engwordlockscreen.presentation.lockscreen.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engwordlockscreen.domain.database.WordEntities
import com.example.engwordlockscreen.domain.usecase.quizusecases.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
) : ViewModel()
{
    private val _times = MutableStateFlow<String>("")
    var times = _times.asStateFlow()

    private val _correctCount = MutableStateFlow<Int>(0)
    var correctCount = _correctCount.asStateFlow()

    private val _correctList = MutableStateFlow<MutableList<WordEntities>>(mutableListOf())
    var correctList = _correctList.asStateFlow()

    init {
        viewModelScope.launch {
            getMultiChoiceList().collect { wordList ->
                val corList = wordList.groupBy { it.word }.values.random().toMutableList()
                _correctList.emit(corList)
                _correctCount.emit(corList.size)
                Log.d("TRANSFER11111","$corList, ${corList.size}")
            }
        }
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
    }.stateIn(viewModelScope)

    suspend fun judgeWord(word : WordEntities) = when (_correctList.value.contains(word)){
        true -> {
            _correctList.value.remove(word)
            _correctCount.emit(correctList.value.size)
            true
        }
        false ->{
            false
        }
    }
}