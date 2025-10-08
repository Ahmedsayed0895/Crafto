package org.example.project.presentation.screens.onboarding.composable

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.textstyle.AppTheme

@Composable
fun OnboardingIndicator(
    currentPage: Int,
    totalPage: Int,
    modifier: Modifier = Modifier,
    progressColor: Color = AppTheme.craftoColors.brand.primary,
    trackColor: Color = AppTheme.craftoColors.background.card,
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        repeat(totalPage) { index ->
            val width = animateDpAsState(
                targetValue = if (index == currentPage) 32.dp else 16.dp,
                animationSpec = spring(
                    dampingRatio = 0.70f,
                    stiffness = 50f
                ),
                label = "indicatorWidth"
            )

            val color = animateColorAsState(
                targetValue = if (index == currentPage) progressColor else trackColor,
                animationSpec = tween(300),
                label = "indicatorColor"
            )

            Box(
                modifier = Modifier
                    .width(width.value)
                    .height(8.dp)
                    .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                    .background(color = color.value)
            )
        }
    }
}