package org.example.project.designSystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


enum class ButtonState {
    Enable, LOADING, DISABLED
}


@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter? = null,
    buttonState: ButtonState = ButtonState.DISABLED,
    enabled: Boolean,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit,
    cornerRadius: Dp = AppTheme.craftoRadius.full,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {
    Button(
        onClick = { onClick() },
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(cornerRadius),
        colors = colors,
        contentPadding = contentPadding,
        content = {
            AnimatedVisibility(buttonState == ButtonState.LOADING) {
                CraftoCircularProgressIndicator(size = 24.dp)
            }
            AnimatedVisibility(buttonState != ButtonState.LOADING) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (icon != null) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = icon,
                            contentDescription = null,
                            tint = AppTheme.craftoColors.button.onSecondary
                        )
                    }
                    Text(
                        text = text,
                        style = AppTheme.textStyle.body.medium,
                    )
                }
            }
        }
    )

}

@Preview
@Composable
private fun DefaultButtonPreview(){
    AppTheme{
        Column {
            DefaultButton(
                text = "Secondary Button",
                enabled = true,
                buttonState = ButtonState.Enable,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                colors= ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.additional.primaryRed,
                    contentColor = AppTheme.craftoColors.background.card,
                    disabledContainerColor = AppTheme.craftoColors.background.card,
                    disabledContentColor = AppTheme.craftoColors.shade.secondary
                ) ,
                onClick = {}
            )
            DefaultButton(
                text = "Secondary Button",
                enabled = false,
                buttonState = ButtonState.DISABLED,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                colors= ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.additional.primaryBlue,
                    contentColor = AppTheme.craftoColors.background.card,
                    disabledContainerColor = AppTheme.craftoColors.background.card,
                    disabledContentColor = AppTheme.craftoColors.shade.secondary
                ) ,
                onClick = {}
            )
            DefaultButton(
                text = "Secondary Button",
                enabled = true,
                buttonState = ButtonState.LOADING,
                contentPadding = PaddingValues(vertical = 15.dp, horizontal = 24.dp),
                colors= ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.button.secondary,
                    contentColor = AppTheme.craftoColors.button.onSecondary,
                    disabledContainerColor = AppTheme.craftoColors.background.card,
                    disabledContentColor = AppTheme.craftoColors.shade.secondary
                ) ,
                onClick = {}
            )
        }

    }
}