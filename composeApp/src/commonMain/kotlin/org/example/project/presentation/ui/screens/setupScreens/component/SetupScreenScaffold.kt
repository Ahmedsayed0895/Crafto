package org.example.project.presentation.ui.screens.setupScreens.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun SetupScreenScaffold(
    modifier: Modifier = Modifier,
    currentPageNumber: Int,
    title: String,
    description: String,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    content: @Composable () -> Unit,
)