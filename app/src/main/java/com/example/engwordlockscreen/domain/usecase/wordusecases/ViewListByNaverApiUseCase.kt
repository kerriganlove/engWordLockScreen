package com.example.engwordlockscreen.domain.usecase.wordusecases

import com.example.engwordlockscreen.constants.Response
import com.example.engwordlockscreen.domain.remote.WordApiModel
import com.example.engwordlockscreen.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow

class ViewListByNaverApiUseCase(
    private val repository: WordRepository
) {
    suspend operator fun invoke(engWord : String) : Flow<Response<List<WordApiModel>>> {
        return repository.getWordListByApi(engWord)
    }
}