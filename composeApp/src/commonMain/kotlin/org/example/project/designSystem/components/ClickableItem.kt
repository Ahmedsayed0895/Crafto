package org.example.project.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.alt_arrow_right
import crafto.composeapp.generated.resources.logout
import crafto.composeapp.generated.resources.sledgehammer
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ClickableItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Painter,
    iconColor: Color = AppTheme.craftoColors.shade.secondary,
    title: String,
    titleColor: Color = AppTheme.craftoColors.shade.primary,
    trailingIcon: @Composable () -> Unit = {
        Icon(
            painter = painterResource(Res.drawable.alt_arrow_right),
            contentDescription = "Chevron right",
            tint = AppTheme.craftoColors.shade.tertiary,
        )
    }
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.craftoColors.shade.quaternary)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource()
                )
                { onClick() }
                .background(AppTheme.craftoColors.shade.quaternary)
                .padding(vertical = 19.dp),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                modifier = Modifier
                    .padding(end = 8.dp),
                painter = icon,
                contentDescription = "$title icon",
                tint = iconColor,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = AppTheme.textStyle.body.medium,
                color = titleColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            trailingIcon()
        }
    }
}

@Preview()
@Composable
fun ClickableItemPreview() {
    AppTheme {
        ClickableItem(
            onClick = {},
            icon = painterResource(Res.drawable.sledgehammer),
            title = "Switch to Craftsman",
        )
    }

}

@Preview()
@Composable
fun ClickableItemPreview_logout() {
    AppTheme {
        ClickableItem(
            onClick = {},
            icon = painterResource(Res.drawable.logout),
            iconColor = AppTheme.craftoColors.additional.primaryError,
            title = "Logout",
            titleColor = AppTheme.craftoColors.additional.primaryError,
        )
    }

}
