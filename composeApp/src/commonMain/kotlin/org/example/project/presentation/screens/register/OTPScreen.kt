package org.example.project.presentation.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.do_not_receive_code
import crafto.composeapp.generated.resources.enter_verification_code
import crafto.composeapp.generated.resources.otp
import crafto.composeapp.generated.resources.resent_code
import crafto.composeapp.generated.resources.sent_message_to_phone
import crafto.composeapp.generated.resources.verify
import org.example.project.presentation.designsystem.components.BackButton
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.components.SecondaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OTPScreen() {
    OTPContent(
        phoneNumber = "01279336697",
        onVerifyButtonClick = {},
        onResendCodeButtonClick = {},
        onBackButtonClick = {}
    )
}

@Composable
private fun OTPContent(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    onVerifyButtonClick: () -> Unit,
    onResendCodeButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit
) {


    var otpList by remember { mutableStateOf(List(6) { "" }) }
    val focusManager = LocalFocusManager.current
    val focusRequesters = List(otpList.size) { FocusRequester() }

    Box(
        modifier = modifier.fillMaxSize().background(AppTheme.craftoColors.background.screen)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .align(Alignment.Start)
            )
            {
                BackButton(
                    modifier = Modifier.background(
                        color = AppTheme.craftoColors.background.card,
                        shape = RoundedCornerShape(AppTheme.craftoRadius.full)
                    ),
                    onClick = onBackButtonClick
                )
            }

            Image(
                painter = painterResource(Res.drawable.otp),
                contentDescription = "otp image",
            )

            Box(
                modifier = Modifier.fillMaxSize().background(
                    color = AppTheme.craftoColors.background.card,
                    shape = RoundedCornerShape(
                        topStart = AppTheme.craftoRadius.x5l,
                        topEnd = AppTheme.craftoRadius.x5l
                    ),
                ).padding(horizontal = 24.dp)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                        .windowInsetsPadding(WindowInsets.ime)
                ) {
                    Text(
                        text = stringResource(Res.string.enter_verification_code),
                        style = AppTheme.textStyle.title.medium,
                        color = AppTheme.craftoColors.shade.primary,
                        modifier = Modifier.padding(top = 40.dp, bottom = 8.dp)
                    )

                    Text(
                        text = stringResource(Res.string.sent_message_to_phone),
                        style = AppTheme.textStyle.body.mediumRegular,
                        color = AppTheme.craftoColors.shade.secondary,
                    )
                    Text(
                        text = phoneNumber,
                        style = AppTheme.textStyle.body.medium,
                        color = AppTheme.craftoColors.shade.primary,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    Row(
                        modifier = Modifier.padding(bottom = 24.dp)
                    ) {

                        repeat(otpList.size) { index ->
                            OTPField(
                                text = otpList[index],
                                modifier = Modifier.weight(1f).focusRequester(focusRequesters[index]),
                                onTextChange = { value ->
                                    val digit = value.filter { it.isDigit() }.take(1)
                                    val updateList= otpList.toMutableList().also { list ->
                                        list[index] = digit
                                    }
                                    otpList = updateList

                                    if (value.isNotEmpty()) {
                                        if (index < otpList.size - 1) {
                                            focusRequesters[index + 1].requestFocus()
                                        } else {
                                            focusManager.clearFocus()
                                        }
                                    } else {
                                        if (index > 0) {
                                            focusRequesters[index - 1].requestFocus()
                                        }
                                    }
                                }
                            )
                        }
                    }

                    PrimaryButton(
                        text = stringResource(Res.string.verify),
                        enabled = true,
                        onClick = onVerifyButtonClick,
                        buttonState = ButtonState.Enable,
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
                    )

                    Text(
                        text = stringResource(Res.string.do_not_receive_code),
                        style = AppTheme.textStyle.body.mediumRegular,
                        color = AppTheme.craftoColors.shade.secondary,
                        modifier = Modifier
                            .padding(top = 24.dp, bottom = 12.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    SecondaryButton(
                        text = stringResource(Res.string.resent_code),
                        enabled = true,
                        onClick = onResendCodeButtonClick,
                        buttonState = ButtonState.Enable,
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = AppTheme.craftoColors.shade.quinary,
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
                    )
                }
            }

        }
    }
}

@Composable
private fun OTPField(
    text: String,
    hint: String = "0",
    modifier: Modifier,
    onTextChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier.background(AppTheme.craftoColors.background.card)
            .padding(horizontal = 5.dp, vertical = 8.dp).onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        singleLine = true,
        textStyle = AppTheme.textStyle.title.large.copy(
            textAlign = TextAlign.Center,
            color = AppTheme.craftoColors.shade.primary
        ),
        shape = RoundedCornerShape(AppTheme.craftoRadius.lg),
        placeholder = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (text.isEmpty() && !isFocused) {
                    Text(
                        hint,
                        textAlign = TextAlign.Center,
                        style = AppTheme.textStyle.title.large,
                        color = AppTheme.craftoColors.shade.tertiary
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
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
            disabledLabelColor = AppTheme.craftoColors.shade.tertiary,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )
}


@Preview
@Composable
private fun OTPScreenPreview() {
    AppTheme {
        OTPScreen()
    }
}