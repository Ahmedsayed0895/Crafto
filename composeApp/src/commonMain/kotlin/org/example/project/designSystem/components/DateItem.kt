package org.example.project.designSystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.clock_circle
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DateItem(
    value : String,
    icon : Painter,
    modifier : Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            contentDescription = "icon",
            tint = AppTheme.craftoColors.shade.secondary,
            modifier = Modifier.size(16.dp).padding(end = 4.dp)
        )

        Text(
            text = value,
            style = AppTheme.textStyle.body.smallMedium,
            color = AppTheme.craftoColors.shade.secondary
        )
    }
}


@Preview
@Composable
private fun DateAndTimePreview(){
    AppTheme(isDarkTheme = false) {
        DateItem(
            value = "10 Jul, 2025",
            icon = painterResource(Res.drawable.clock_circle)
        )
    }
}
