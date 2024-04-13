package com.clean.sample.domain.repository

import com.clean.sample.data.remote.BaseResponse
import com.clean.sample.domain.model.login.request.LoginRequest
import com.clean.sample.domain.model.login.response.LoginResponse
import retrofit2.Call

interface OnBoardingRepository {

    fun doLogin(loginRequest: LoginRequest): Call<BaseResponse<LoginResponse>>
}