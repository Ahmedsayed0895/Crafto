package org.example.project.presentation.screens.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.continue_button
import crafto.composeapp.generated.resources.registration_step_1_description
import crafto.composeapp.generated.resources.user_type
import org.example.project.domain.entity.UserType
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.TextButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.setup.composable.SetupScreenScaffold
import org.example.project.presentation.screens.setup.composable.page.UserTypeSelectionPage
import org.example.project.presentation.shared.base.ErrorUiState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserTypeSelectionScreen(
    viewModel: UserTypeSelectionViewModel = koinViewModel(),
    onNavigateToSetup: (UserType) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is UserTypeSelectionEffect.NavigateToSetup -> {
                    onNavigateToSetup(effect.userType)
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        UserTypeSelectionContent(
            state = state,
            viewModel = viewModel,
        )

        AnimatedVisibility(
            visible = state.error != null,
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            state.error?.let { error ->
                ErrorSnackbar(
                    error = error,
                    onDismiss = viewModel::clearError,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun UserTypeSelectionContent(
    state: UserTypeSelectionUiState,
    viewModel: UserTypeSelectionInteractionListener,
) {
    SetupScreenScaffold(
        currentPageNumber = 1,
        totalPages = 1,
        title = stringResource(Res.string.user_type),
        description = stringResource(Res.string.registration_step_1_description),
        nextButtonText = stringResource(Res.string.continue_button),
        nextButtonEnabled = state.selectedType != null && !state.isLoading,
        nextButtonState = when {
            state.isLoading -> ButtonState.LOADING
            state.selectedType != null -> ButtonState.Enable
            else -> ButtonState.DISABLED
        },
        showBackButton = false,
        onNextButtonClick = viewModel::onContinueClick
    ) {
        UserTypeSelectionPage(
            selectedType = state.selectedType,
            onTypeSelected = viewModel::onUserTypeSelected,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun ErrorSnackbar(
    error: ErrorUiState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Snackbar(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        containerColor = AppTheme.craftoColors.additional.primaryRed.copy(alpha = 0.95f),
        contentColor = AppTheme.craftoColors.button.onPrimary,
        action = {
            TextButton(
                onClick = onDismiss,
                text = "Dismiss",
                enabled = true,
                buttonState = ButtonState.Enable
            )
        }
    ) {
        Text(
            text = error.message,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.button.onPrimary
        )
    }
}