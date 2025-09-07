package org.example.project.designSystem.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.example.project.designSystem.colors.ExtraColors
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * A customizable Switch component based on Material3.
 *
 * @param isChecked Whether the switch is checked.
 * @param onCheckedChange Callback when the checked state changes.
 * @param modifier Modifier to be applied to the switch.
 * @param checkedThumbColor Color of the thumb when checked.
 * @param checkedTrackColor Color of the track when checked.
 * @param uncheckedThumbColor Color of the thumb when unchecked.
 * @param uncheckedTrackColor Color of the track when unchecked.
 */
@Composable
fun Switch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    checkedThumbColor: Color = ExtraColors.white,
    checkedTrackColor: Color = AppTheme.craftoColors.brand.primary,
    uncheckedThumbColor: Color = AppTheme.craftoColors.shade.secondary,
    uncheckedTrackColor: Color = AppTheme.craftoColors.background.card
) {
    Switch(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = checkedThumbColor,
            checkedTrackColor = checkedTrackColor,
            uncheckedThumbColor = uncheckedThumbColor,
            uncheckedTrackColor = uncheckedTrackColor
        )
    )
}

@Preview
@Composable
fun SwitchPreviewChecked() {
    Switch(isChecked = true, onCheckedChange = {})
}

@Preview
@Composable
fun SwitchPreviewUnchecked() {
    Switch(isChecked = false, onCheckedChange = {})
}