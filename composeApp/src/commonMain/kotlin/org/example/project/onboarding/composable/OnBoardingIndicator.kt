package org.example.project.onboarding.composable

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
fun OnBoardingIndicator(
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
            Box(
                modifier = Modifier
                    .width(if (index  == currentPage) 32.dp else 16.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                    .background(
                        color = if (index  == currentPage) progressColor else trackColor
                    )
            )
        }
    }
}