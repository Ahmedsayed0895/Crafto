package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.di.IosModule
import org.example.project.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin {
            modules(IosModule().module)
        }
    }
) { App() }