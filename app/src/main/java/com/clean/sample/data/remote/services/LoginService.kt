package com.clean.sample.data.remote.services

import com.clean.sample.data.remote.BaseResponse
import com.clean.sample.domain.model.login.request.LoginRequest
import com.clean.sample.domain.model.login.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

open interface LoginService {

    @POST("/api/authaccount/login")
    fun doLogin(@Body loginRequest: LoginRequest): Call<BaseResponse<LoginResponse>>
}