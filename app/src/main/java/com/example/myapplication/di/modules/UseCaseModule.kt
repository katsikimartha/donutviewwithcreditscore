package com.example.myapplication.di.modules

import com.example.myapplication.repo.CreditScoreRepository
import com.example.myapplication.usecase.GetCreditScoreUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetCreditScoreUseCase(repository: CreditScoreRepository) = GetCreditScoreUseCase(repository)

}