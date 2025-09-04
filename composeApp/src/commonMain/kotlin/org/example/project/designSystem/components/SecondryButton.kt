package org.example.project.designSystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.example.project.designSystem.textStyle.AppTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    buttonState: ButtonState,
    contentPadding: PaddingValues=ButtonDefaults.ContentPadding,
    onClick: () -> Unit,
){
    val contentColor by animateColorAsState(
        if(buttonState==ButtonState.LOADING)AppTheme.craftoColors.button.primary else AppTheme.craftoColors.button.onSecondary
    )
    DefaultButton(
        modifier = modifier,
        text = text,
        buttonState =buttonState,
        enabled =enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.craftoColors.button.secondary,
            contentColor = contentColor,
            disabledContainerColor = AppTheme.craftoColors.button.disabled,
            disabledContentColor = AppTheme.craftoColors.button.onDisabled
        ),
        onClick = {onClick()},
        contentPadding = contentPadding,
    )
}