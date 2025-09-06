package org.example.project.designSystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.check_mark
import crafto.composeapp.generated.resources.selection_card_img
import crafto.composeapp.generated.resources.verified_check_1
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CircleAvatar(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.
        clip(RoundedCornerShape(AppTheme.craftoRadius.full))
            .size(55.dp)
            .background(Color.Transparent)
    ) {
        Box (modifier = modifier.align(Alignment.Center)){
            Image(
                painter = image,
                contentDescription = "Avater image",
                modifier = Modifier.fillMaxSize().clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }

        Icon(
            painter = painterResource(Res.drawable.verified_check_1),
            contentDescription = "Verified",
            modifier = Modifier.size(16.dp).align(Alignment.BottomCenter),
            tint = AppTheme.craftoColors.additional.primarySuccess
        )

        Icon(
            painter = painterResource(Res.drawable.check_mark),
            contentDescription = "check mark",
            modifier = Modifier.size(16.dp).align(Alignment.BottomCenter),
            tint = AppTheme.craftoColors.background.card
        )
    }
}

@Preview
@Composable
private fun CircleAvatarPreview() {
    AppTheme(isDarkTheme = false) {
        CircleAvatar(
            modifier = Modifier
                .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                .size(40.dp)
                .background(AppTheme.craftoColors.background.card),
            image = painterResource(Res.drawable.selection_card_img)
        )
    }
}
