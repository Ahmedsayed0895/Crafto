package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.di.initKoin
import org.example.project.di.iosModule

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {
            modules(iosModule)
        }
    }
) { App() }