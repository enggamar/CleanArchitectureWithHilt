package com.clean.sample.data.remote

import okhttp3.Headers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException

abstract class NetworkResponseCallBack<T> : Callback<BaseResponse<T>> {

    abstract fun onResponse(result: NetworkResponse<T>?)

    override fun onResponse(
        responseCallback: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>
    ) {

        response.body()?.let {
            val data = response.body()!!.data
            val statusCode = response.body()!!.statusCode
            val message = response.body()!!.message
            onResponse(NetworkResponse.Success(data, statusCode, message))
            handleAccessTokenUpdate(response.headers())
        } ?: onResponse(NetworkResponse.Failure(getFailureResponse(response)))

    }

    private fun getFailureResponse(response: Response<BaseResponse<T>>): NetworkError {
        val statusCode = response.code()
        val apiFailureError = ApiFailureError(statusCode, Status.UNKNOWN_ERROR, response.message());
        when (statusCode) {
            401 -> {
                apiFailureError.status = Status.SESSION_EXPIRED
            }

            else -> {
                if (statusCode >= 500) {
                    apiFailureError.status = Status.SESSION_EXPIRED
                    apiFailureError.message = "Something Went Wrong"
                }
            }
        }
        return NetworkError.ApiError(apiFailureError)
    }

    /**
     * This function is used to Handle access token update from headers
     *
     * @param headers
     */
    private fun handleAccessTokenUpdate(headers: Headers) {
        TODO("Not yet implemented")
    }

    /**
     * On failure
     *
     * @param responseCallback
     * @param error
     */
    override fun onFailure(responseCallback: Call<BaseResponse<T>>, error: Throwable) {
        onResponse(NetworkResponse.Failure(NetworkError.UnknownError(error)))
    }

}