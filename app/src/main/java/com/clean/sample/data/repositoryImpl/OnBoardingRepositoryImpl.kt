package com.clean.sample.data.repositoryImpl

import com.clean.sample.data.remote.BaseResponse
import com.clean.sample.data.remote.services.ApiService
import com.clean.sample.domain.model.login.request.LoginRequest
import com.clean.sample.domain.model.login.response.LoginResponse
import com.clean.sample.domain.repository.OnBoardingRepository
import retrofit2.Call
import javax.inject.Inject

class OnBoardingRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    OnBoardingRepository {

    override   fun doLogin(loginRequest: LoginRequest): Call<BaseResponse<LoginResponse>> {
        return apiService.doLogin(loginRequest)
    }
}