package org.example.project.presentation.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.and_text
import crafto.composeapp.generated.resources.continue_button
import crafto.composeapp.generated.resources.egypt_flag
import crafto.composeapp.generated.resources.enter_phone
import crafto.composeapp.generated.resources.logo
import crafto.composeapp.generated.resources.logo_icon
import crafto.composeapp.generated.resources.phone_hint
import crafto.composeapp.generated.resources.privacy_agreement
import crafto.composeapp.generated.resources.privacy_policy
import crafto.composeapp.generated.resources.terms_and_conditions
import crafto.composeapp.generated.resources.welcome_title
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.components.TextField
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
) {
    RegisterContent(
        modifier = modifier,
        onPrivacyPolicyClick = {},
        onTermsClick = {},
        onButtonClick = {}
    )
}

@Composable
private fun RegisterContent(
    modifier: Modifier,
    onPrivacyPolicyClick: () -> Unit,
    onTermsClick: () -> Unit,
    onButtonClick: () -> Unit
) {
    var number by remember { mutableStateOf("") }
    Box(
        modifier = modifier.fillMaxSize().background(AppTheme.craftoColors.brand.primary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.padding(top = 75.dp).background(
                    AppTheme.craftoColors.background.card, shape = RoundedCornerShape(
                        AppTheme.craftoRadius.full
                    )
                ).size(64.dp)

            ) {
                Icon(
                    painter = painterResource(Res.drawable.logo),
                    contentDescription = stringResource(Res.string.logo_icon),
                    modifier = Modifier.align(Alignment.Center).offset(x = (-5).dp, y = (5).dp),
                    tint = AppTheme.craftoColors.brand.primary
                )
            }

            Text(
                text = stringResource(Res.string.welcome_title),
                style = AppTheme.textStyle.title.large,
                textAlign = TextAlign.Center,
                color = AppTheme.craftoColors.background.card,
                modifier = Modifier.padding(top = 16.dp, start = 24.dp, bottom = 67.dp)
            )
            Box(
                modifier = Modifier.fillMaxSize().background(
                    color = AppTheme.craftoColors.background.card,
                    shape = RoundedCornerShape(
                        topStart = AppTheme.craftoRadius.x5l,
                        topEnd = AppTheme.craftoRadius.x5l
                    )
                ).padding(horizontal = 24.dp)
            ) {
                Column {
                    Text(
                        text = stringResource(Res.string.enter_phone),
                        style = AppTheme.textStyle.title.medium,
                        color = AppTheme.craftoColors.shade.primary,
                        modifier = Modifier.padding(top = 40.dp, bottom = 24.dp)
                    )

                    TextField(
                        hint = stringResource(Res.string.phone_hint),
                        startIcon = {
                            Image(
                                painter = painterResource(Res.drawable.egypt_flag),
                                contentDescription = stringResource(Res.string.egypt_flag),
                                modifier = Modifier.padding(start = 18.dp)
                            )
                        },
                        showDividerLine = true,
                        maxLines = 1,
                        minLines = 1,
                        showPhoneCode = true,
                        text = number,
                        onTextChange = { if (it.length <= 10) number = it },
                        inputKeyboard = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Default
                        )
                    )

                    PrivacyAndTextSection(
                        normalText = stringResource(Res.string.privacy_agreement),
                        specialText = stringResource(Res.string.terms_and_conditions),
                        onClick = onTermsClick,
                        modifier = Modifier.padding(top = 12.dp).fillMaxWidth()

                    )

                    PrivacyAndTextSection(
                        normalText = stringResource(Res.string.and_text),
                        specialText = stringResource(Res.string.privacy_policy),
                        onClick = onPrivacyPolicyClick,
                        modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth()
                    )

                    PrimaryButton(
                        text = stringResource(Res.string.continue_button),
                        enabled = true,
                        onClick = onButtonClick,
                        buttonState = ButtonState.Enable,
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun PrivacyAndTextSection(
    modifier: Modifier = Modifier,
    normalText: String,
    specialText: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = normalText,
            style = AppTheme.textStyle.body.smallRegular,
            color = AppTheme.craftoColors.shade.secondary
        )
        Text(
            text = specialText,
            style = AppTheme.textStyle.body.smallRegular,
            color = AppTheme.craftoColors.brand.primary,
            modifier = Modifier.clickable { onClick }
        )
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreen()
    }
}