package com.example.engwordlockscreen.domain.usecase.wordusecases

data class WordUseCases(
    val deleteWordUseCase: DeleteWordUseCase,
    val insertWordUseCase: InsertWordUseCase,
    val sameWordUseCase: SameWordUseCase,
    val viewListUseCase: ViewListUseCase,
    val deleteAllWordUseCase: DeleteAllWordUseCase
                       )
