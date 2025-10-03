package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.ui.screens.setupScreens.AccountSetupCategoryScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        AccountSetupCategoryScreen()
    }
}