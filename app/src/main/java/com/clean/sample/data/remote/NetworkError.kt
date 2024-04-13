package com.clean.sample.data.remote

sealed class NetworkError {
    object failedToConnect : NetworkError()
    data class UnknownError(val throwable: Throwable?) : NetworkError()
    data class ApiError(val apiFailureError: ApiFailureError) : NetworkError()
}
