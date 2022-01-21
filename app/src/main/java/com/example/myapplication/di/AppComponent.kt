package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import com.example.myapplication.di.modules.ActivityModule
import com.example.myapplication.di.modules.AndroidModule
import com.example.myapplication.di.modules.AppModule
import com.example.myapplication.di.modules.RepositoryModule
import com.example.myapplication.di.modules.UseCaseModule
import com.example.myapplication.di.modules.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,
    AndroidModule::class, AppModule::class,
    ActivityModule::class,
    UseCaseModule::class,
    RepositoryModule::class,
    ViewModelModule::class ])

interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun app(app: Context): Builder

        fun androidModule(androidModule: AndroidModule): Builder
        fun appModule(appModule: AppModule): Builder
    }
}