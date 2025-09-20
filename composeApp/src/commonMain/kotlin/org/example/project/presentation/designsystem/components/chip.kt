package org.example.project.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    textColor: Color,
    borderColor: Color = AppTheme.craftoColors.brand.secondary,
    onChipSelected: (String) -> Unit = {},
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
            .background(
                if (isSelected)
                    AppTheme.craftoColors.brand.tertiary
                else
                    AppTheme.craftoColors.shade.quinary,
                shape = RoundedCornerShape(AppTheme.craftoRadius.full)
            )
            .then(
                if (isSelected) modifier.border(
                    1.dp, borderColor,
                    RoundedCornerShape(
                        AppTheme.craftoRadius.full
                    )
                )
                else modifier
            )

            .clickable(
                enabled = true,
                onClick = { onChipSelected(text) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            color = textColor,
            style = AppTheme.textStyle.label.medium,
            textAlign = TextAlign.Center,
            maxLines = 1
        )

    }
}

@Preview
@Composable
private fun ChipPreview() {
    val selectedChip by remember { mutableStateOf(true) }
    AppTheme(isDarkTheme =true) {
        Chip(
            text = "Label",
            isSelected = selectedChip,
            textColor = if (selectedChip) AppTheme.craftoColors.brand.primary else AppTheme.craftoColors.shade.secondary,

        )
    }
}