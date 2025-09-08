package org.example.project.designSystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.user_rounded
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DashedCircle(
    icon: Painter,
    dashedLineColor: Color,
    tintColor : Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = dashedLineColor,
                    style = Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 10f), 0f)
                    ),
                    cornerRadius = CornerRadius(size.minDimension / 2)
                )
            }
    ) {
        Icon(
            painter = icon,
            contentDescription = "icon",
            tint = tintColor,
            modifier = Modifier.align(Alignment.Center).size(20.dp)
        )
    }
}

@Preview
@Composable
private fun DashedCirclePreview() {
    AppTheme(isDarkTheme = false) {
        DashedCircle(
            modifier = Modifier.clip(RoundedCornerShape(AppTheme.craftoRadius.full)).size(40.dp),
            icon = painterResource(Res.drawable.user_rounded),
            dashedLineColor = AppTheme.craftoColors.shade.quaternary,
            tintColor = AppTheme.craftoColors.shade.tertiary
        )
    }
}