package com.clean.sample.di

import com.clean.sample.data.remote.services.ApiService
import com.clean.sample.data.repositoryImpl.OnBoardingRepositoryImpl
import com.clean.sample.domain.repository.OnBoardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOnBoardingRepo(apiService: ApiService): OnBoardingRepository {
        return OnBoardingRepositoryImpl(apiService)
    }
}
