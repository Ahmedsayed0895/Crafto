package org.example.project

import android.app.Application
import org.example.project.di.initKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin ()
    }
}