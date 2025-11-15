package org.example.project

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.navigation.CraftoNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        val navController = rememberNavController()
        CraftoNavGraph(navController = navController)
    }
}