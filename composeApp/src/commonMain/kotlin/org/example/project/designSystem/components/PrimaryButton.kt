package org.example.project.designSystem.components


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.designSystem.textStyle.AppTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    buttonState: ButtonState,
    contentPadding: PaddingValues=ButtonDefaults.ContentPadding,
    onClick: () -> Unit,
){
    DefaultButton(
        modifier = modifier,
        text = text,
        buttonState =buttonState,
        enabled =enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.craftoColors.button.primary,
            contentColor = AppTheme.craftoColors.button.onPrimary,
            disabledContainerColor = AppTheme.craftoColors.button.disabled,
            disabledContentColor = AppTheme.craftoColors.button.onDisabled
        ),
        onClick = {onClick()},
        contentPadding = contentPadding,
    )
}