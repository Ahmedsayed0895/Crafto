package org.example.project.designSystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme


enum class ButtonState {
    Enable, LOADING, DISABLED
}


@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonState:ButtonState=ButtonState.DISABLED,
    enabled: Boolean,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit,
    contentPadding: PaddingValues=ButtonDefaults.ContentPadding
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(AppTheme.craftoRadius.full),
        colors =colors,
        contentPadding = contentPadding,
        content = {
            AnimatedVisibility(buttonState == ButtonState.LOADING) {
                CraftoCircularProgressIndicator(size = 24.dp)
            }
            AnimatedVisibility(buttonState != ButtonState.LOADING) {
                Text(
                    text = text,
                    style = AppTheme.textStyle.body.medium,
                    )
            }
        }
    )

}