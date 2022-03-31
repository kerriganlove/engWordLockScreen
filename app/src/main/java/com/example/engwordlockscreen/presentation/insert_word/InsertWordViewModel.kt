package com.example.engwordlockscreen.presentation.insert_word

import androidx.lifecycle.ViewModel
import com.example.engwordlockscreen.domain.usecase.InsertWordUseCase
import javax.inject.Inject

class InsertWordViewModel @Inject constructor(
    private val insertWordUseCase: InsertWordUseCase
    ) : ViewModel()
{

}