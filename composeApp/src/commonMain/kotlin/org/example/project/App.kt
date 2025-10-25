package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.setupscreens.craftsmansetup.CraftsmanSetupScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        //OnboardingScreen()
        CraftsmanSetupScreen()
    }
}