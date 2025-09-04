package org.example.project.designSystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.project.designSystem.textStyle.AppTheme

@Composable
fun TextButton(
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
            containerColor = Color.Transparent,
            contentColor = AppTheme.craftoColors.button.onSecondary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = AppTheme.craftoColors.button.onDisabled
        ),
        onClick = {onClick()},
        contentPadding =contentPadding,
    )
}