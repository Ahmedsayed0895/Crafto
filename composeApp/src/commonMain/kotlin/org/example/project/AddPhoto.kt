package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.camera
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddPhoto(
    modifier: Modifier = Modifier,
    dashedLineColor: Color,
) {
    val cornerRadius = AppTheme.craftoRadius.lg
    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = dashedLineColor,
                    style = Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
                    ),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }.padding(vertical = 24.5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(Res.drawable.camera),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = AppTheme.craftoColors.shade.secondary
            )
            Text(
                text = "Tap to add photo",
                color = AppTheme.craftoColors.shade.secondary,
                style = AppTheme.textStyle.body.smallMedium,
            )
        }

    }
}

@Preview()
@Composable
private fun AddPhotoPreview(){
    AddPhoto(
        dashedLineColor=AppTheme.craftoColors.shade.quaternary,
    )
}