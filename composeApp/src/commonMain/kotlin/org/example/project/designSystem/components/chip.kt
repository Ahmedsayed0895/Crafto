package org.example.project.designSystem.components

import androidx.compose.foundation.background

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onChipSelected: (String) -> Unit = {},
) {
    Row(
        modifier = modifier
            .background(
                if (isSelected)
                    AppTheme.craftoColors.brand.tertiary
                else
                    AppTheme.craftoColors.shade.quinary,
                shape = RoundedCornerShape(AppTheme.craftoRadius.full)
            )
            .then(
                if (isSelected) Modifier.border(
                    1.dp, AppTheme.craftoColors.brand.secondary, RoundedCornerShape(
                        AppTheme.craftoRadius.full
                    )
                )
                else Modifier
            )
            .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
            .clickable(
                enabled = true,
                onClick = { onChipSelected(text) }
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            color = if (isSelected) AppTheme.craftoColors.brand.primary else AppTheme.craftoColors.shade.secondary,
            style = AppTheme.textStyle.label.medium,
            textAlign = TextAlign.Center,
        )

    }
}

@Preview
@Composable
private fun ChipPreview() {
    var selectedChip by remember { mutableStateOf(true) }
    AppTheme(isDarkTheme =true) {
        Chip(
            text = "Label",
            isSelected = selectedChip,
        )
    }
}