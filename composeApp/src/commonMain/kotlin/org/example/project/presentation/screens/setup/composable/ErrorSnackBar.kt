package org.example.project.presentation.screens.setup.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.TextButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.shared.base.ErrorUiState


@Composable
fun ErrorSnackBar(
    error: ErrorUiState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Snackbar(
        modifier = modifier.padding(16.dp),
        shape = RoundedCornerShape( 8.dp),
        containerColor = AppTheme.craftoColors.additional.primaryRed.copy(alpha = 0.95f),
        contentColor = AppTheme.craftoColors.button.onPrimary,
        action = {
            TextButton(
                onClick = onDismiss, text = "Dismiss",
                enabled = true, buttonState = ButtonState.Enable
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