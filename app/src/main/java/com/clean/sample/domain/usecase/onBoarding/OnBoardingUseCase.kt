package com.clean.sample.domain.usecase.onBoarding

import android.util.Log
import android.widget.Toast
import com.clean.sample.data.remote.BaseResponse
import com.clean.sample.data.remote.NetworkResponse
import com.clean.sample.data.remote.NetworkResponseCallBack
import com.clean.sample.domain.model.login.request.LoginRequest
import com.clean.sample.domain.model.login.response.LoginResponse
import com.clean.sample.domain.repository.OnBoardingRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import javax.inject.Inject

open class OnBoardingUseCase @Inject constructor(val useCaseRepo: OnBoardingRepository) {

     fun loginUser(loginRequest: LoginRequest): LoginResponse? {
        useCaseRepo.doLogin(loginRequest)
            .enqueue(object : NetworkResponseCallBack<LoginResponse>() {
                override fun onResponse(result: NetworkResponse<LoginResponse>?) {
                    Log.d("OnBoardingUseCase", "onResponse: " +"Success"+result.toString())
                }

                override fun onFailure(
                    responseCallback: Call<BaseResponse<LoginResponse>>,
                    error: Throwable
                ) {
                    Log.d("OnBoardingUseCase", "onResponse: " +"failure")
                }

            })
        return null
     }
}