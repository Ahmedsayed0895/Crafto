package org.example.project.designSystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.ic_error
import crafto.composeapp.generated.resources.ic_eye__closed
import crafto.composeapp.generated.resources.ic_eye_opened
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private const val s = "Forgot Password?"

@Composable
fun TextField(
    labelText: String? = null,
    showDividerLine : Boolean = false,
    hint: String? = null,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    showAsPassword: Boolean = false,
    revealPassword: Boolean = false,
    onRevealPasswordToggle: (() -> Unit)? = null,
    readOnlyMode: Boolean = false,
    enabledState: Boolean = true,
    errorState: Boolean = false,
    errorHint: String? = null,
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
    maxLines: Int = 1,
    minLines: Int = 1,
    allowSingleLine: Boolean = true,
    textAppearance: TextStyle? = null,
    textTint: Color? = null,
    inputKeyboard: KeyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
    inputActions: KeyboardActions = KeyboardActions.Default,
    forgotAction: (() -> Unit)? = null,
    transformation: VisualTransformation = VisualTransformation.None,
) {
    var internalRevealPassword by remember { mutableStateOf(revealPassword) }
    val currentRevealPassword =
        if (onRevealPasswordToggle != null) revealPassword else internalRevealPassword
    val togglePassword: () -> Unit =
        onRevealPasswordToggle ?: { internalRevealPassword = !internalRevealPassword }

    val semanticDescription = buildString {
        if (labelText != null) append("$labelText. ")
        if (hint != null) append("Hint: $hint. ")
        if (errorState && !errorHint.isNullOrEmpty()) append("Error: $errorHint. ")
        if (showAsPassword) append("Password field.")
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.semantics { contentDescription = semanticDescription }
    ) {
        if (labelText != null) {
            Text(
                text = labelText,
                style = AppTheme.textStyle.body.medium,
                color = if (enabledState) AppTheme.craftoColors.shade.secondary else AppTheme.craftoColors.shade.tertiary
            )
        }

        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            readOnly = readOnlyMode,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    AppTheme.craftoColors.background.card,
                    RoundedCornerShape(AppTheme.craftoRadius.lg)
                ),
            textStyle = textAppearance ?: AppTheme.textStyle.body.medium,
            placeholder = hint?.let {
                {
                    Text(
                        text = it,
                        style = AppTheme.textStyle.body.medium,
                        color = AppTheme.craftoColors.shade.tertiary,
                        maxLines = 1,
                        overflow = if (allowSingleLine) TextOverflow.Ellipsis else TextOverflow.Clip
                    )
                }
            },
            singleLine = allowSingleLine,
            maxLines = maxLines,
            minLines = minLines,
            isError = errorState,
            enabled = enabledState,
            leadingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    startIcon?.invoke()
                    if(showDividerLine) VerticalDivider()
                }
            },
            trailingIcon = {
                Box {
                    if (showAsPassword) {
                        IconButton(onClick = togglePassword) {
                            val iconPainter = if (currentRevealPassword) {
                                painterResource(Res.drawable.ic_eye_opened)
                            } else {
                                painterResource(Res.drawable.ic_eye__closed)
                            }
                            Icon(
                                painter = iconPainter,
                                contentDescription = if (currentRevealPassword) "Hide password" else "Show password"
                            )
                        }
                    }
                    if (errorState) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_error),
                            contentDescription = "Error icon",
                            tint = AppTheme.craftoColors.additional.primaryRed,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                    endIcon?.invoke()
                }
            },
            visualTransformation = if (showAsPassword && !currentRevealPassword)
                PasswordVisualTransformation()
            else transformation,
            keyboardOptions = inputKeyboard,
            keyboardActions = inputActions,
            shape = RoundedCornerShape(AppTheme.craftoRadius.lg),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = textTint ?: AppTheme.craftoColors.shade.primary,
                focusedTextColor = AppTheme.craftoColors.shade.primary,
                focusedBorderColor = AppTheme.craftoColors.brand.primary,
                unfocusedBorderColor = AppTheme.craftoColors.stroke.primary,
                errorBorderColor = AppTheme.craftoColors.additional.primaryRed,
                errorTextColor = AppTheme.craftoColors.shade.primary,
                cursorColor = AppTheme.craftoColors.brand.primary,
                errorCursorColor = AppTheme.craftoColors.additional.primaryRed,
                disabledTextColor = AppTheme.craftoColors.shade.primary,
                disabledBorderColor = AppTheme.craftoColors.stroke.primary,
                disabledPlaceholderColor = AppTheme.craftoColors.shade.tertiary,
                disabledLeadingIconColor = AppTheme.craftoColors.shade.tertiary,
                disabledTrailingIconColor = AppTheme.craftoColors.shade.tertiary,
                disabledLabelColor = AppTheme.craftoColors.shade.tertiary,
            )
        )

        AnimatedVisibility(visible = errorState && !errorHint.isNullOrEmpty()) {
            Text(
                text = errorHint!!,
                color = AppTheme.craftoColors.additional.primaryRed,
                style = AppTheme.textStyle.body.smallMedium,
            )
        }

        if (showAsPassword && forgotAction != null) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Forgot Password?",
                    textDecoration = TextDecoration.Underline,
                    style = AppTheme.textStyle.body.medium,
                    color = if (enabledState) AppTheme.craftoColors.shade.secondary else AppTheme.craftoColors.shade.tertiary,
                    modifier = Modifier.clickable { forgotAction() }
                )
            }
        }
    }
}

@Composable
private fun VerticalDivider() {
    Box(
        Modifier
            .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
            .width(1.dp)
            .height(24.dp)
            .background(AppTheme.craftoColors.stroke.primary)
    )
}


@Preview
@Composable
private fun TextFieldPreview() {
    AppTheme(isDarkTheme = true) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            var text1 by remember { mutableStateOf("") }
            TextField(
                labelText = "Label",
                hint = "Value",
                text = text1,
                onTextChange = { text1 = it },
                enabledState = true
            )

            var text2 by remember { mutableStateOf("Mus") }
            TextField(
                labelText = "Label",
                hint = "Enter value",
                text = text2,
                onTextChange = { text2 = it },
                errorState = true,
                errorHint = "Error message",
                enabledState = true
            )

            var text3 by remember { mutableStateOf("") }
            TextField(
                labelText = "Label",
                hint = "Value",
                text = text3,
                onTextChange = { text3 = it },
                enabledState = true
            )

            var text4 by remember { mutableStateOf("") }
            TextField(
                labelText = "Label",
                hint = "Value",
                text = text4,
                onTextChange = { text4 = it },
                showAsPassword = true,
                forgotAction = { },
                enabledState = true
            )
        }
    }
}