package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.navigation.CraftoNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        val navController = rememberNavController()
        CraftoNavGraph(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.craftoColors.background.screen)
                .navigationBarsPadding())
    }
}