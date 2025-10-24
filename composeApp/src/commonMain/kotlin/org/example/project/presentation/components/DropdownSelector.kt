package org.example.project.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.components.TextField
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import crafto.composeapp.generated.resources.*

@Composable
fun DropdownSelector(
    text: String,
    hint: String,
    icon: DrawableResource,
    hasSelection: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = AppTheme.craftoColors.background.card,
                shape = RoundedCornerShape(AppTheme.craftoRadius.lg)
            )
            .clickable(
                onClick = onClick,
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .semantics { role = Role.Button },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            hint = hint,
            text = text,
            onTextChange = {},
            readOnlyMode = true,
            enabledState = false,
            startIcon = {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = if (hasSelection) AppTheme.craftoColors.shade.primary
                    else AppTheme.craftoColors.shade.tertiary
                )
            },
            endIcon = {
                Icon(
                    painter = painterResource(Res.drawable.alt_arrow_down),
                    contentDescription = stringResource(Res.string.dropdown_icon),
                    tint = if (hasSelection) AppTheme.craftoColors.shade.primary
                    else AppTheme.craftoColors.shade.tertiary
                )
            }
        )
    }
}