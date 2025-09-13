package org.example.project.designSystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    buttonState: ButtonState,
    cornerRadius: Dp=AppTheme.craftoRadius.full,
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
        cornerRadius = cornerRadius,
        onClick = {onClick()},
        contentPadding = contentPadding,
    )
}

@Preview
@Composable
private fun SecondaryButtonPreview(){
    AppTheme{
        Column {
            SecondaryButton(
                text = "Secondary Button",
                enabled = true,
                buttonState = ButtonState.Enable,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                onClick = {}
            )
            SecondaryButton(
                text = "Secondary Button",
                enabled = false,
                buttonState = ButtonState.DISABLED,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                onClick = {}
            )
            SecondaryButton(
                text = "Secondary Button",
                enabled = true,
                buttonState = ButtonState.LOADING,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                onClick = {}
            )
        }

    }
}