package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.components.ButtonState
import org.example.project.designSystem.components.PrimaryButton
import org.example.project.designSystem.components.SecondaryButton
import org.example.project.designSystem.components.TextButton
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
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
