package com.example.engwordlockscreen.presentation.lockscreen.quiz

sealed class QuizEvent{
    data class MultiChoice(val s : String) : QuizEvent()
    data class Puzzle(val s : String) : QuizEvent()
}
