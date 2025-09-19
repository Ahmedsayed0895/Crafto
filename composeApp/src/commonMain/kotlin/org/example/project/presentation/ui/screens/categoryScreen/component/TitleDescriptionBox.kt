package org.example.project.presentation.ui.screens.categoryScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.textstyle.AppTheme


@Composable
fun TitleDescriptionBox(
    modifier: Modifier,
    title: String,
    description: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(), contentAlignment = Alignment.BottomStart

    ) {
        TitleDescriptionText(
            title = title,
            description = description
        )

    }
}

@Composable
private fun TitleDescriptionText(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = title,
            style = AppTheme.textStyle.display,
            color = AppTheme.craftoColors.shade.primary
        )
        Text(
            text = description,
            style = AppTheme.textStyle.body.largeRegular,
            color = AppTheme.craftoColors.shade.secondary
        )
    }
}

