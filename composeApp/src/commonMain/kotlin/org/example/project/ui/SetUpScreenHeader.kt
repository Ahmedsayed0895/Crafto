package org.example.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.arrow_left
import org.example.project.designSystem.components.ProgressIndicator
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource

@Composable
fun ScreenHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(48.dp).clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                .background(AppTheme.craftoColors.background.card),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(Res.drawable.arrow_left),
                contentDescription = "arrow left",
                modifier = Modifier.size(24.dp),
                tint = AppTheme.craftoColors.shade.primary
            )
        }
        ProgressIndicator(
            currentPage = 4,
            totalPage = 4,
            modifier = Modifier.fillMaxWidth(0.8f).padding(start = 16.dp),
            progressColor = AppTheme.craftoColors.brand.primary,
            trackColor = AppTheme.craftoColors.background.card
        )
    }
}
