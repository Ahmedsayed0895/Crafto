package org.example.project.designSystem.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean,
    buttonState: ButtonState,
    contentPadding: PaddingValues=ButtonDefaults.ContentPadding,
    cornerRadius: Dp=AppTheme.craftoRadius.full,
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
        cornerRadius = cornerRadius,
        onClick = {onClick()},
        contentPadding = contentPadding,
    )
}

@Preview
@Composable
private fun PrimaryButtonPreview(){
    AppTheme{
        Column {
            PrimaryButton(
                enabled = true,
                onClick = {},
                text = "Button",
                buttonState = ButtonState.Enable,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
            )
            PrimaryButton(
                enabled = false,
                onClick = {},
                text = "Button",
                buttonState = ButtonState.DISABLED,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
            )

            PrimaryButton(
                enabled = true,
                onClick = {},
                text = "Button",
                buttonState = ButtonState.LOADING,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
            )
        }

    }
}