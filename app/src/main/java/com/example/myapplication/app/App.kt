package com.example.myapplication.app

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class App : Application(), HasAndroidInjector {
    @Inject
    protected lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()

        initialiseDagger()
        initialiseLogging()
    }

    abstract fun initialiseDagger()

    abstract fun initialiseLogging()

}