package org.example.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.arrow_left
import crafto.composeapp.generated.resources.i_ll_verify_later
import crafto.composeapp.generated.resources.optional
import crafto.composeapp.generated.resources.see_nearby_requests
import crafto.composeapp.generated.resources.upload_back_of_national_id
import crafto.composeapp.generated.resources.upload_front_of_national_id
import crafto.composeapp.generated.resources.verify_identity_description
import crafto.composeapp.generated.resources.verify_your_identity
import org.example.project.designSystem.components.ButtonState
import org.example.project.designSystem.components.PrimaryButton
import org.example.project.designSystem.components.ProgressIndicator
import org.example.project.designSystem.components.SecondaryButton
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun VerifyIdentityScreen() {

    VerifyIdentityContent()
}


@Composable
fun VerifyIdentityContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(AppTheme.craftoColors.background.screen)
            .statusBarsPadding().padding(16.dp)
    ) {
        ScreenHeader(modifier = Modifier.padding(bottom = 32.dp))
        VerifyText(modifier = Modifier.padding(bottom = 32.dp, top = 58.dp))
        TextUpload(
            text = stringResource(Res.string.upload_front_of_national_id),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        AddPhoto(
            dashedLineColor = AppTheme.craftoColors.shade.quaternary,
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            background = AppTheme.craftoColors.background.bottomSheet,
            onClick = {},
        )
        TextUpload(
            text = stringResource(Res.string.upload_back_of_national_id),
            modifier = Modifier.padding(bottom = 12.dp)
        )
        AddPhoto(
            dashedLineColor = AppTheme.craftoColors.shade.quaternary,
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
            background = AppTheme.craftoColors.background.bottomSheet,
            onClick = {}
        )

        SecondaryButton(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            text = stringResource(Res.string.i_ll_verify_later),
            enabled = true,
            buttonState = ButtonState.Enable,
            onClick = {},
            contentPadding = PaddingValues(vertical = 15.dp)
        )
        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.see_nearby_requests),
            enabled = true,
            buttonState = ButtonState.Enable,
            onClick = {},
            contentPadding = PaddingValues(vertical = 15.dp)
        )


    }
}


@Composable
fun ScreenHeader(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp).clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                .background(AppTheme.craftoColors.background.card),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.arrow_left),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = AppTheme.craftoColors.shade.primary
            )

        }
        ProgressIndicator(
            currentPage = 4,
            totalPage = 4,
            modifier = Modifier.fillMaxWidth(0.8f).padding(start = 16.dp),
            progressColor = AppTheme.craftoColors.brand.primary,
            trackColor = AppTheme.craftoColors.background.card
        )
    }
}

@Composable
fun VerifyText(modifier: Modifier = Modifier) {
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
private fun VerifyIdentityPreview() {
    AppTheme {
        VerifyIdentityScreen()
    }
}


