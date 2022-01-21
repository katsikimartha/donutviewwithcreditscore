package com.example.myapplication.di.modules

import com.example.myapplication.apiclient.ConnectionClient
import com.example.myapplication.apiclient.ConnectionClientInterface
import com.example.myapplication.base.RemoteConfig
import com.example.myapplication.di.PrivateImplementation
import com.example.myapplication.utils.DefaultDispatchers
import com.example.myapplication.utils.DispatcherProvider
import com.example.myapplication.utils.ExceptionManager
import com.example.myapplication.utils.ExceptionMapper
import com.example.myapplication.utils.bus.LiveDataBus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {

    @Provides
    @Singleton
    fun providesConnectionClientInterface(
        @PrivateImplementation connectionClient: ConnectionClient
    ): ConnectionClientInterface {
        return connectionClient
    }

    @Provides
    @Singleton
    @PrivateImplementation
    fun providesConnectionClient(
        remoteConfig: RemoteConfig
    ) =
        ConnectionClient(
            mobileAPIBaseUrl = remoteConfig.getFullMobileAPIBaseUrl(),
            mapiHeaders = remoteConfig.getDebugMAPIHeaders()
        )


    @Deprecated(
        message = "Classes should be injected by abstraction",
        replaceWith = ReplaceWith("provideNetworkExceptionMapper")
    )
    @Provides
    @Singleton
    fun provideNetworkExceptionManager() = ExceptionManager(LiveDataBus)

    @Provides
    fun provideNetworkExceptionMapper(exceptionManager: ExceptionManager): ExceptionMapper = exceptionManager

    @Provides
    @Singleton
    fun providesDispatcherProvider(): DispatcherProvider = DefaultDispatchers()

    @Provides
    @Singleton
    fun provideRemoteConfig() = RemoteConfig()
}