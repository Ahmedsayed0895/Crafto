package org.example.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.i_will_verify_later
import crafto.composeapp.generated.resources.optional
import crafto.composeapp.generated.resources.see_nearby_requests
import crafto.composeapp.generated.resources.upload_back_of_national_id
import crafto.composeapp.generated.resources.upload_front_of_national_id
import crafto.composeapp.generated.resources.verify_identity_description
import crafto.composeapp.generated.resources.verify_your_identity
import org.example.project.designSystem.components.ButtonState
import org.example.project.designSystem.components.PrimaryButton
import org.example.project.designSystem.components.SecondaryButton
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun IdentityVerificationScreen() {

    IdentityVerificationContent()
}

@Composable
fun IdentityVerificationContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(AppTheme.craftoColors.background.screen)
            .statusBarsPadding().padding(16.dp)
    ) {
        ScreenHeader(modifier = Modifier.padding(bottom = 32.dp))
        TextVerification(modifier = Modifier.padding(bottom = 32.dp, top = 58.dp))
        TextUpload(
            text = stringResource(Res.string.upload_front_of_national_id),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        PhotoUploaded(
            dashedLineColor = AppTheme.craftoColors.shade.quaternary,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            background = AppTheme.craftoColors.background.bottomSheet,
            onClick = {},
        )
        TextUpload(
            text = stringResource(Res.string.upload_back_of_national_id),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        PhotoUploaded(
            dashedLineColor = AppTheme.craftoColors.shade.quaternary,
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            background = AppTheme.craftoColors.background.bottomSheet,
            onClick = {}
        )
        Spacer(modifier = Modifier.weight(1f))
        SecondaryButton(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            text = stringResource(Res.string.i_will_verify_later),
            enabled = true,
            buttonState = ButtonState.Enable,
            onClick = {},
            contentPadding = PaddingValues(vertical = 15.dp)
        )
        PrimaryButton(
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
            text = stringResource(Res.string.see_nearby_requests),
            enabled = true,
            buttonState = ButtonState.Enable,
            onClick = {},
            contentPadding = PaddingValues(vertical = 15.dp)
        )
    }
}
@Composable
fun TextVerification(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(Res.string.verify_your_identity))
                withStyle(
                    style = SpanStyle(
                        color = AppTheme.craftoColors.shade.secondary
                    )
                ) {
                    append(stringResource(Res.string.optional))
                }
            },
            color = AppTheme.craftoColors.shade.primary,
            style = AppTheme.textStyle.display
        )
        Text(
            text = stringResource(Res.string.verify_identity_description),
            color = AppTheme.craftoColors.shade.secondary,
            style = AppTheme.textStyle.body.largeRegular
        )

    }
}

@Composable
fun TextUpload(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        color = AppTheme.craftoColors.shade.primary,
        style = AppTheme.textStyle.body.mediumRegular,
        modifier = modifier

    )
}

@Preview
@Composable
private fun IdentityVerificationPreview() {
    AppTheme {
        IdentityVerificationScreen()
    }
}


