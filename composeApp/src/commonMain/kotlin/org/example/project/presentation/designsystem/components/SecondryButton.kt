package org.example.project.presentation.designsystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.project.presentation.designsystem.textstyle.AppTheme

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    buttonState: ButtonState,
    contentPadding: PaddingValues=ButtonDefaults.ContentPadding,
    containerColor: Color = AppTheme.craftoColors.button.secondary,
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
            containerColor =containerColor,
            contentColor = contentColor,
            disabledContainerColor = AppTheme.craftoColors.button.disabled,
            disabledContentColor = AppTheme.craftoColors.button.onDisabled
        ),
        onClick = {onClick()},
        contentPadding = contentPadding,
    )
}