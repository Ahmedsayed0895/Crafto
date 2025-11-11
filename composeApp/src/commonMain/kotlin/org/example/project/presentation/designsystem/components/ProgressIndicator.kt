package org.example.project.presentation.designsystem.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun ProgressIndicator(
    currentPage: Int,
    totalPage: Int,
    animationDuration: Int = 300,
    modifier: Modifier = Modifier,
    progressColor: Color = AppTheme.craftoColors.brand.primary,
    trackColor: Color = AppTheme.craftoColors.background.card,
) {

    val animatedProgress by animateFloatAsState(
        targetValue = (currentPage.toFloat() / totalPage.toFloat()).coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = animationDuration),
        label = "progress_animation"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(8.dp)
            .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
            .background(
                color = trackColor,
                RoundedCornerShape(AppTheme.craftoRadius.full)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(animatedProgress)
                .height(8.dp)
                .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                .background(
                    color = progressColor,
                    RoundedCornerShape(AppTheme.craftoRadius.full)
                )
        )
    }
}

@Preview
@Composable
private fun ProgressIndicatorPreview() {
    ProgressIndicator(
        totalPage = 4,
        currentPage = 2,
        modifier = Modifier.padding(horizontal = 80.dp)
    )
}