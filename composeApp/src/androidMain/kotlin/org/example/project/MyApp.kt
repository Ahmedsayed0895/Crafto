package org.example.project

import android.app.Application
import org.example.project.di.initKoin
import org.example.project.di.networkModule
import org.example.project.di.platformModule
import org.koin.android.ext.koin.androidContext

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApp)
            modules(networkModule, platformModule)
        }

    }
}