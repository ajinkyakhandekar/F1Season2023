package com.ajinkya.formula1.core.data

sealed class ResponseStatus<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(data: T? = null) : ResponseStatus<T>(data)
    class Success<T>(data: T?) : ResponseStatus<T>(data)
    class Error<T>(message: String, data: T? = null) : ResponseStatus<T>(data, message)
}
