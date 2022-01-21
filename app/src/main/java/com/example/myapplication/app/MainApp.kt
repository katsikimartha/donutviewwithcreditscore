package com.example.myapplication.app

import com.example.myapplication.di.modules.AndroidModule
import com.example.myapplication.di.modules.AppModule
import com.example.myapplication.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber


class MainApp : App() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
    }

    override fun initialiseDagger() {
        DaggerAppComponent.builder()
            .androidModule(AndroidModule(this))
            .appModule(AppModule())
            .app(this)
            .build()
            .inject(this)
    }

    override fun initialiseLogging() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return super.createStackElementTag(element) + ":" + element.lineNumber
            }
        })
    }
}