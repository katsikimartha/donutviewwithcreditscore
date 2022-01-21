package com.example.myapplication.di.mock.module

import com.example.myapplication.apiclient.ConnectionClient
import com.example.myapplication.apiclient.ConnectionClientInterface
import com.example.myapplication.base.RemoteConfig
import com.example.myapplication.utils.ExceptionManager
import com.example.myapplication.utils.ExceptionMapper
import com.example.myapplication.utils.bus.LiveDataBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule() {

    @Provides
    @Singleton
    fun provideConnectionClient(): ConnectionClientInterface {
        return ConnectionClient(
            "https://android-interview.s3.eu-west-2.amazonaws.com/",
            null
        )
    }

    @Provides
    @Singleton
    fun provideNetworkExceptionManager() = ExceptionManager(LiveDataBus)

    @Provides
    fun provideNetworkExceptionMapper(exceptionManager: ExceptionManager): ExceptionMapper = exceptionManager

    @Provides
    fun provideRemoteConfig() = RemoteConfig()

}