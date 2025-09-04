package org.example.project.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.waterdrops
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryItemVertical(
    icon: Painter,
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .widthIn(min = 200.dp)
            .background(
                color = AppTheme.craftoColors.background.card,
                shape = RoundedCornerShape(AppTheme.craftoRadius.x2l),
            ).clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp).background(
                color = AppTheme.craftoColors.additional.secondaryBlue,
                shape = RoundedCornerShape(AppTheme.craftoRadius.xl)
            )
        ) {
            Icon(
                painter = icon,
                contentDescription = "category item icon",
                tint = AppTheme.craftoColors.additional.primaryBlue,
                modifier = Modifier.padding(10.dp)
            )
        }

        Text(
            text = title,
            color = AppTheme.craftoColors.shade.primary,
            style = AppTheme.textStyle.body.medium,
            modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 4.dp)
        )
        Text(
            text = description,
            color = AppTheme.craftoColors.shade.secondary,
            style = AppTheme.textStyle.label.medium,
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
        )

    }
}

@Preview
@Composable
private fun CategoryItemPreview() {
    AppTheme(isDarkTheme = false) {
        CategoryItemVertical(
            icon = painterResource(Res.drawable.waterdrops),
            title = "Title",
            description = "Description",
            onClick = {},
        )
    }
}