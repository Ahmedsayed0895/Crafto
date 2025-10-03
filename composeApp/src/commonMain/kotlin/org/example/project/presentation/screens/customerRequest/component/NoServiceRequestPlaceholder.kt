package org.example.project.presentation.screens.customerRequest.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.no_request_placeholder
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NoServiceRequestPlaceholder(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.no_request_placeholder),
            contentDescription = "No request",
            modifier = Modifier.size(120.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = "No Service Requests Yet",
            style = AppTheme.textStyle.title.small,
            color = AppTheme.craftoColors.shade.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Start by choosing a service and submitting your first request.",
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.shade.secondary,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
private fun NoServiceRequestPlaceholderPreview() {
    AppTheme(
        isDarkTheme = false
    ) {
        NoServiceRequestPlaceholder(
            modifier = Modifier.fillMaxSize()
                .background(AppTheme.craftoColors.background.screen)
                .padding(horizontal = 40.dp)
        )
    }
}