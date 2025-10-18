package org.example.project.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.arrow_left
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BackButton(
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
){
    Box(
        modifier = modifier
    ){
        Icon(
            painter = painterResource(Res.drawable.arrow_left),
            contentDescription = "arrow left icon",
            modifier = Modifier.align(Alignment.Center).clickable(onClick = onClick)
                .padding(12.dp)
        )
    }
}

@Preview
@Composable
private fun BackButtonPreview(){
    AppTheme(isDarkTheme = false) {
        BackButton(
            modifier = Modifier.background(
                color = AppTheme.craftoColors.background.card,
                shape = RoundedCornerShape(AppTheme.craftoRadius.full)
            ),
            onClick = {}
        )
    }
}