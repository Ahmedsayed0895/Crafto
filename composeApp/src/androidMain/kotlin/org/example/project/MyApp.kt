package org.example.project

import android.app.Application
import org.example.project.di.androidModule
import org.example.project.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApp)
            androidLogger()
            modules(androidModule)
        }
    }
}