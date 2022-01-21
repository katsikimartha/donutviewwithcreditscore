package com.example.myapplication.di.modules

import com.example.myapplication.apiclient.ConnectionClientInterface
import com.example.myapplication.di.repo.AppRepository
import com.example.myapplication.di.repo.AppRepositoryImp
import com.example.myapplication.repo.CreditScoreRepository
import com.example.myapplication.repo.CreditScoreRepositoryImpl
import com.example.myapplication.utils.DispatcherProvider
import com.example.myapplication.utils.ExceptionManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesAppRepository(
        connectionClient: ConnectionClientInterface, exceptionManager: ExceptionManager, dispatchers: DispatcherProvider
    ): AppRepository =
        AppRepositoryImp(connectionClient, exceptionManager, dispatchers)

    @Provides
    @Singleton
    fun providesCreditScoreRepository(
        connectionClient: ConnectionClientInterface,
        exceptionManager: ExceptionManager,
        dispatchers: DispatcherProvider
    ): CreditScoreRepository =
        CreditScoreRepositoryImpl(
            connectionClient,
            exceptionManager,
            dispatchers
        )
}