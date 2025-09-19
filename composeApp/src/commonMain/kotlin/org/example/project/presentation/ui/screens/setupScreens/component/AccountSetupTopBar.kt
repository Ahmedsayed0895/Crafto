package org.example.project.presentation.ui.screens.setupScreens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.arrow_left
import org.example.project.presentation.designsystem.components.ProgressIndicator
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AccountSetupTopBar(
    modifier: Modifier = Modifier,
    currentPage: Int,
    onBackButtonClick: () -> Unit

) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        BackButton(onBackButtonClick = onBackButtonClick)
        ProgressIndicator(
            currentPage = currentPage,
            totalPage = 4,
            modifier = Modifier.fillMaxWidth(0.75f),
        )
    }
}

@Composable
private fun BackButton(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(AppTheme.craftoColors.background.card)
            .clickable(onClick = onBackButtonClick),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            painter = painterResource(Res.drawable.arrow_left),
            contentDescription = "back button",
            tint = AppTheme.craftoColors.shade.primary
        )
    }
}


@Preview
@Composable
fun AccountSetupTopBarLightPreview() {
    AppTheme {
        AccountSetupTopBar(
            onBackButtonClick = {},
            currentPage = 2
        )

    }
}

@Preview
@Composable
fun AccountSetupTopBarDarkPreview() {
    AppTheme(isDarkTheme = true) {
        AccountSetupTopBar(
            onBackButtonClick = {},
            currentPage = 2

        )
    }
}