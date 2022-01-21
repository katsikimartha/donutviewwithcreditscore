package com.example.myapplication

import android.content.Context
import com.example.myapplication.app.App
import com.example.myapplication.di.modules.ActivityModule
import com.example.myapplication.di.modules.UseCaseModule
import com.example.myapplication.di.modules.ViewModelModule
import com.example.myapplication.di.mock.module.TestAppModule
import com.example.myapplication.di.mock.module.TestRepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    TestAppModule::class, ActivityModule::class, UseCaseModule::class,
    TestRepositoryModule::class,
    ViewModelModule::class])

interface TestAppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): TestAppComponent

        @BindsInstance
        fun app(app: Context): Builder

        fun appModule(appModule: TestAppModule): Builder
    }
}