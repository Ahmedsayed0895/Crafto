package org.example.project

import android.app.Application
import initKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin ()
    }
}