package org.example.project.presentation.screens.customerRequest.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.textstyle.AppTheme

@Composable
fun DividedLine() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(vertical = 16.dp)
            .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
            .background(AppTheme.craftoColors.shade.quaternary)
    )
}
