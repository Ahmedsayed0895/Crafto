package org.example.project.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ProgressIndicator(
    progress: Int,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.craftoColors.brand.primary,
    trackColor: Color = AppTheme.craftoColors.background.card,
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(1000.dp))
            .background(
                color = trackColor,
                RoundedCornerShape(1000.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth((progress * 0.25f).coerceIn(0f, 1f))
                .height(8.dp)
                .clip(RoundedCornerShape(1000.dp))
                .background(
                    color = color,
                    RoundedCornerShape(1000.dp)
                )
        )

    }

}

@Preview
@Composable
private fun ProgressIndicatorPreview() {
    ProgressIndicator(
        progress = 2,
        modifier = Modifier.padding(horizontal = 80.dp)
    )
}