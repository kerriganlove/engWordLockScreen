package com.example.engwordlockscreen.constants

sealed class UiState<T>(val data : T? = null, val msg : String? = null, val time : Int? = null ) {
    data class Loading<T>(val load_msg : String) : UiState<T>(msg = load_msg)
    data class Success<T>(val suc_data : T) : UiState<T>(data = suc_data)
    data class Fail<T>(val err_data : T, val err_msg : String? = null) : UiState<T>(data = err_data, msg = err_msg)
    data class RefreshData<T>(val second : Int?) : UiState<T>(time = second)
}