package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.onboarding.OnboardingScreen
import org.example.project.presentation.screens.setupScreens.location.LocationSetupScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        LocationSetupScreen()
    }
}