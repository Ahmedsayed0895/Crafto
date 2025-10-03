package org.example.project.presentation.util.extension

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Color.toAnimatedColor(
    condition: Boolean,
    falseConditionColor: Color,
    easing: Easing = EaseInCirc,
    duration: Int = 300
): Color {
    return animateColorAsState(
        if (condition) this else falseConditionColor, animationSpec = tween(
            easing = easing,
            durationMillis = duration
        )
    ).value
}