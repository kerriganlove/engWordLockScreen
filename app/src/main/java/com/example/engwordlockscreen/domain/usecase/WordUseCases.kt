package com.example.engwordlockscreen.domain.usecase

import java.util.*

data class WordUseCases(
    private val deleteWordUseCase: DeleteWordUseCase,
    private val insertWordUseCase: InsertWordUseCase,
    private val sameWordUseCase: SameWordUseCase,
    private val viewListUseCase: ViewListUseCase
                       )
