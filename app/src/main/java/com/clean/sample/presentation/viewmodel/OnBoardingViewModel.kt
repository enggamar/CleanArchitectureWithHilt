package com.clean.sample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.clean.sample.domain.model.login.request.LoginRequest
import com.clean.sample.domain.usecase.onBoarding.OnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(private val useCase: OnBoardingUseCase) :
    ViewModel() {


    fun loginUser() {
        useCase.loginUser(LoginRequest("Amar kumar", "testiong"))
    }
}