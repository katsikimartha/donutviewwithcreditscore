package com.example.myapplication.di.mock.module

import com.example.myapplication.apiclient.ConnectionClientInterface
import com.example.myapplication.di.repo.AppRepositoryImp
import com.example.myapplication.repo.CreditScoreRepository
import com.example.myapplication.repo.CreditScoreRepositoryImpl
import com.example.myapplication.utils.DispatcherProvider
import com.example.myapplication.utils.ExceptionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestRepositoryModule {

    @Provides
    @Singleton
    fun providesAppRepository(
        networkClient: ConnectionClientInterface,
        exceptionManager: ExceptionManager,
        dispatchers: DispatcherProvider,
    ) =
        AppRepositoryImp(networkClient, exceptionManager, dispatchers)

    @Provides
    @Singleton
    fun providesCreditScoreRepository(
        networkClient: ConnectionClientInterface,
        exceptionManager: ExceptionManager,
        dispatchers: DispatcherProvider,
    ): CreditScoreRepository =
        CreditScoreRepositoryImpl(
            networkClient,
            exceptionManager,
            dispatchers
        )

}