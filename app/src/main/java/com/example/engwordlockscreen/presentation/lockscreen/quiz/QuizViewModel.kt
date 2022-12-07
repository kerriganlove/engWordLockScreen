package com.example.engwordlockscreen.presentation.lockscreen.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engwordlockscreen.constants.UiState
import com.example.engwordlockscreen.domain.database.WordEntities
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

    private val _correctList = MutableStateFlow<List<WordEntities>>(emptyList())
    var correctList = _correctList.asStateFlow()

    private val _correct = WordEntities()

    init {

    }

    /*
     * MultiChoice Quiz Data
     */
    suspend fun getMultiChoiceList() = quizUseCases.multiChoiceUseCase().map { wordList ->
        when(wordList.size) {
            in 0..3 -> {
                UiState.Fail(err_data = wordList.size)
            }
            else -> {
                val list = wordList.shuffled().take(9)
                val subList = list.groupBy { it.word }.values.random()
                _correctList.emit(subList)
                UiState.Success(list)
            }
        }
    }.catch { e -> UiState.Fail(err_data = 0, err_msg = "$e") }.stateIn(viewModelScope)

    /*
     * Puzzle Quiz Data
     */

    suspend fun getPuzzleList() = quizUseCases.puzzleUseCases().map { wordList ->
        when(wordList.size) {
            in 0..4 -> {
                UiState.Fail(err_data = wordList.size)
            }
            else -> {
                UiState.Success(wordList.groupBy { it.word }
                    .values.shuffled()
                    .map { it.random() }
                    .take(5))
            }
        }
    }.catch { e -> UiState.Fail(err_data = 0, err_msg = "$e") }.stateIn(viewModelScope)

    /*
     * Judge Word
     */
    suspend fun judgeWordByList(word : WordEntities) : Boolean {
        val corList = _correctList.value.toMutableList()
        return when (corList.contains(word)){
            true -> {
                corList.remove(word)
                _correctList.emit(corList)
                true
            }
            false ->{
                false
            }
        }
    }

    fun judgeWord(word : WordEntities) : Boolean {
        return _correct == word
    }
}