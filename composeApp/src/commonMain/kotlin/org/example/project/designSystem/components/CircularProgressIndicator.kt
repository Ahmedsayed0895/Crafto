package org.example.project.designSystem.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CraftoCircularProgressIndicator(
    modifier: Modifier = Modifier,
    strokeRatio: Float = 0.20f,
    size: Dp = 48.dp,
    animationDuration: Int = 1000,
    initialColor: Color = AppTheme.craftoColors.brand.primary,
    targetColor: Color = AppTheme.craftoColors.brand.tertiary
) {
    val transition = rememberInfiniteTransition(label = "loading-gradient")

    val rotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing
            )
        ),
        label = "progress"
    )

    val brush = Brush.sweepGradient(
        colors = listOf(
            targetColor,
            initialColor,
        )
    )

    val strokeWidth = (size / 2) * strokeRatio

    Canvas(modifier = modifier.size(size)) {
        rotate(rotation,pivot = center) {
            drawArc(
                brush = brush,
                startAngle = 90f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                size = this.size
            )
        }
    }
}

@Preview()
@Composable
fun CraftoLoadingIndicatorPreview() {
    AppTheme {
        Box(Modifier.fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)) {
        CraftoCircularProgressIndicator()
        }
    }
}