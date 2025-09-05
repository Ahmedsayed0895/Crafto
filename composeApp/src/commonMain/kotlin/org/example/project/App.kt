package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.designSystem.components.CountryCodeSelector
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.project.designSystem.components.CraftoCircularProgressIndicator
import org.example.project.designSystem.components.TestScreen
import org.example.project.designSystem.components.countriesList
import org.example.project.designSystem.textStyle.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TestScreen()
        }
    }
}
