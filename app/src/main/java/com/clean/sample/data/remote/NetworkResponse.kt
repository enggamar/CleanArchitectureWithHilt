package com.clean.sample.data.remote

sealed class NetworkResponse<T> {
    data class Success<T>(val data: T?, val statusCode: Int, val message: String?) : NetworkResponse<T>()
    data class Failure<T>(val error: NetworkError) : NetworkResponse<T>()
}