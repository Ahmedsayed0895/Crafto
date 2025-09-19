package org.example.project.presentation.ui.screens.setupScreens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.next
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun SetupScreenScaffold(
    modifier: Modifier,
    currentPageNumber: Int,
    title: String,
    description: String,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    content: @Composable (() -> Unit)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    )
    {
        AccountSetupTopBar(onBackButtonClick = onBackButtonClick, currentPage = currentPageNumber)
        TitleDescriptionBox(
            modifier = Modifier.weight(1f),
            title = title,
            description = description,
        )
        content()
        PrimaryButton(
            text = stringResource(Res.string.next),
            enabled = true,
            buttonState = ButtonState.Enable,
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextButtonClick
        )

    }
}