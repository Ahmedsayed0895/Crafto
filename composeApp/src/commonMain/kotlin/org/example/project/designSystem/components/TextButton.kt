package org.example.project.designSystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

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
            contentColor = AppTheme.craftoColors.button.onTertiary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = AppTheme.craftoColors.button.onDisabled
        ),
        onClick = {onClick()},
        contentPadding =contentPadding,
    )
}


@Preview
@Composable
private fun TextButtonPreview(){
    AppTheme{
        Column {
            TextButton(
                text = "Text Button",
                enabled = false,
                buttonState = ButtonState.DISABLED,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                onClick = {}
            )
            TextButton(
                text = "Text Button",
                enabled = true,
                buttonState = ButtonState.Enable,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                onClick = {}
            )
            TextButton(
                text = "Text Button",
                enabled = true,
                buttonState = ButtonState.LOADING,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                onClick = {}
            )
        }
    }
}