package org.example.project.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.camera
import crafto.composeapp.generated.resources.tab_to_add_photo
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PhotoUploaded(
    modifier: Modifier = Modifier,
    dashedLineColor: Color,
    background: Color,
    cornerRadius: Dp = AppTheme.craftoRadius.lg,
    onClick: () -> Unit,
) {
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
            }.clickable { onClick() }.background(background, RoundedCornerShape(cornerRadius)).padding(vertical = 24.5.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(Res.drawable.camera),
                contentDescription ="camera",
                modifier = Modifier.size(24.dp),
                tint = AppTheme.craftoColors.shade.secondary
            )
            Text(
                text = stringResource(Res.string.tab_to_add_photo),
                color = AppTheme.craftoColors.shade.secondary,
                style = AppTheme.textStyle.body.smallMedium,
            )
        }

    }
}

@Preview()
@Composable
private fun PhotoUploadedPreview() {
    AppTheme {
        PhotoUploaded(
            modifier = Modifier.fillMaxWidth(),
            dashedLineColor = AppTheme.craftoColors.shade.quaternary,
            background = AppTheme.craftoColors.background.bottomSheet,
            onClick = {}
        )
    }
}