package com.example.engwordlockscreen.constants

sealed class Response<T>(val data : T? = null, val msg : String? = null) {
    data class Success<T>(val suc_data : T) : Response<T>(suc_data)
    data class Error<T>(val err_msg : String, val err_data : T? = null ) : Response<T>(err_data, err_msg)
}

