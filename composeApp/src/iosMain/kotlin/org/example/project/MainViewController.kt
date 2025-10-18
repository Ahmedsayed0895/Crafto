package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }