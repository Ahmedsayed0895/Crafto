package org.example.project

import androidx.compose.runtime.Composable
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.ui.screens.categoryScreen.AccountSetupCategoryScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    AppTheme {
        KoinContext {
            AccountSetupCategoryScreen()
        }
    }
}