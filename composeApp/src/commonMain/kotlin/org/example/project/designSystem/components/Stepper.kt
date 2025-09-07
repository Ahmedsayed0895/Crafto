package org.example.project.designSystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.clipboard_text
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Stepper(
    modifier: Modifier = Modifier, numberOfSteps: Int, currentStep: Int, items: List<StepItem>
) {

    Box(modifier = modifier) {
        TwoColorVerticalDivider(
            modifier = Modifier.fillMaxHeight().align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 6.dp, bottom = 16.dp),
            color1 = AppTheme.craftoColors.brand.primary,
            color2 = AppTheme.craftoColors.shade.quaternary,
            ratio = ((currentStep - 1).toFloat() / (numberOfSteps - 1))
        )
        Column(
            modifier = modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            for (step in 0..numberOfSteps - 1) {
                Step(
                    isCompleted = step < currentStep - 1,
                    isCurrent = step == currentStep - 1,
                    item = items[step]
                )
            }
        }
    }
}

@Composable
fun Step(
    isCompleted: Boolean,
    isCurrent: Boolean,
    item: StepItem
) {
    val circleColor =
        if (isCompleted || isCurrent) AppTheme.craftoColors.brand.primary else AppTheme.craftoColors.shade.quaternary
    val iconBackgroundColor =
        if (isCompleted || isCurrent) AppTheme.craftoColors.brand.primary else AppTheme.craftoColors.button.disabled
    val iconColor =
        if (isCompleted || isCurrent) AppTheme.craftoColors.background.card else AppTheme.craftoColors.shade.tertiary
    val textColor =
        if (isCompleted) AppTheme.craftoColors.shade.primary else if (isCurrent) AppTheme.craftoColors.brand.primary else AppTheme.craftoColors.shade.tertiary

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = item.painter,
                    contentDescription = item.title,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = item.title,
                style = AppTheme.textStyle.body.smallRegular,
                color = textColor,
                maxLines = 1
            )
        }

        Canvas(
            modifier = Modifier.size(12.dp), onDraw = {
                drawCircle(color = circleColor)
            })

    }
}

@Composable
fun TwoColorVerticalDivider(
    modifier: Modifier = Modifier, color1: Color, color2: Color, ratio: Float = 0.5f, // 0.0 to 1.0
    thickness: Float = 2f
) {
    Canvas(modifier = modifier) {
        val height1 = size.height * ratio
        val height2 = size.height * (1 - ratio)
        drawLine(
            color = color1,
            start = Offset(x = size.width / 2, y = 0f),
            end = Offset(x = size.width / 2, y = height1),
            strokeWidth = thickness
        )
        drawLine(
            color = color2,
            start = Offset(x = size.width / 2, y = height1),
            end = Offset(x = size.width / 2, y = height1 + height2),
            strokeWidth = thickness
        )
    }
}

@Preview
@Composable
fun StepsProgressBarPreview() {
    val currentStep = remember { mutableStateOf(2) }
    val items = listOf(
        StepItem("Step 1", painter = painterResource(Res.drawable.clipboard_text)),
        StepItem("Step 2", painter = painterResource(Res.drawable.clipboard_text)),
        StepItem("Step 3", painter = painterResource(Res.drawable.clipboard_text)),
        StepItem("Step 4", painter = painterResource(Res.drawable.clipboard_text)),
        StepItem("Step 5", painter = painterResource(Res.drawable.clipboard_text)),
        StepItem("Step 6", painter = painterResource(Res.drawable.clipboard_text)),
        StepItem("Step 7", painter = painterResource(Res.drawable.clipboard_text)),
    )
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Stepper(
            modifier = Modifier.fillMaxWidth(),
            numberOfSteps = 7,
            currentStep = currentStep.value,
            items
        )
    }
}

data class StepItem(val title: String, val painter: Painter)