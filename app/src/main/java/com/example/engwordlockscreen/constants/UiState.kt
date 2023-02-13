package com.example.engwordlockscreen.constants

sealed class UiState<T>(val data : T? = null, val msg : String? = null, val time : Int? = null ) {
    object Loading : UiState<Nothing>()
    data class Success<T>(val suc_data : T) : UiState<T>(data = suc_data)
    data class Fail<T>(val err_data : T, val err_msg : String? = null) : UiState<T>(data = err_data, msg = err_msg)
    data class RefreshData(val second : Int?) : UiState<Nothing>(time = second)
}