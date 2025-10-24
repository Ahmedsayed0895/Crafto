package org.example.project.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.*
import org.example.project.presentation.designsystem.components.TextField
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GovernorateSelector(
    displayText: String,
    hasSelection: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = AppTheme.craftoColors.background.card,
                shape = RoundedCornerShape(AppTheme.craftoRadius.lg)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            hint = stringResource(Res.string.location_hint),
            text = displayText,
            onTextChange = {},
            modifier = Modifier.fillMaxWidth(),
            startIcon = {
                Icon(
                    painter = painterResource(Res.drawable.location),
                    contentDescription = stringResource(Res.string.location_icon),
                    tint = if (hasSelection) AppTheme.craftoColors.shade.primary else AppTheme.craftoColors.shade.tertiary
                )
            },
            endIcon = {
                Icon(
                    painter = painterResource(Res.drawable.alt_arrow_down),
                    contentDescription = stringResource(Res.string.dropdown_icon),
                    tint = if (hasSelection) AppTheme.craftoColors.shade.primary else AppTheme.craftoColors.shade.tertiary,
                    modifier = Modifier.clickable(onClick = onClick)
                )
            }
        )
    }
}
