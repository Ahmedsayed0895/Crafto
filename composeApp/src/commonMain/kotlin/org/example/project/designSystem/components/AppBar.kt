package org.example.project.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.arrow_left
import crafto.composeapp.generated.resources.notifications
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    caption: String? = null,
    endIcon: Painter? = null,
    hasBackground: Boolean = true,
    showBackButton: Boolean = false,
    onBackButtonClick: () -> Unit = {},
    onEndIconClick: () -> Unit = {},
) {
    val background = if (hasBackground) AppTheme.craftoColors.background.screen else Color.Transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(background)
    ) {
        BackButton(
            showBackButton = showBackButton,
            onBackButtonClick = onBackButtonClick
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Title(
                title = title,
                modifier = Modifier
            )
            Caption(
                caption = caption,
                modifier = Modifier
            )
        }
        EndIcon(painter = endIcon, onEndIconClick = onEndIconClick)
    }
}

@Composable
private fun BackButton(showBackButton: Boolean, onBackButtonClick: () -> Unit) {
    if (showBackButton) {
        Icon(
            painter = painterResource(Res.drawable.arrow_left),
            contentDescription = "arrow back",
            tint = AppTheme.craftoColors.shade.primary,
            modifier = Modifier
                .size(40.dp)
                .clip(shape = CircleShape)
                .clickable(
                    onClick = dropUnlessResumed { onBackButtonClick() }
                )
                .padding(8.dp)
        )
    }
}

@Composable
private fun Caption(caption: String?, modifier: Modifier = Modifier) {
    caption?.let {
        Text(
            text = it,
            maxLines = 1,
            style = AppTheme.textStyle.body.smallMedium,
            color = AppTheme.craftoColors.shade.secondary,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
        )
    }
}

@Composable
private fun Title(title: String?, modifier: Modifier) {
    title?.let {
        Text(
            text = it,
            maxLines = 1,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.shade.primary,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
        )
    }
}

@Composable
private fun EndIcon(painter: Painter?, onEndIconClick: () -> Unit, modifier: Modifier = Modifier) {
    painter?.let {
        Icon(
            painter = it,
            contentDescription = "",
            tint = AppTheme.craftoColors.shade.primary,
            modifier = modifier
                .size(40.dp)
                .clickable(
                    enabled = true,
                    onClick = onEndIconClick
                )
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun AppbarPreview() {
    Column(){
        AppBar(
            title = "Hend Sayed",
            caption = "Cairo,Egypt",
            endIcon = painterResource(Res.drawable.notifications),
            showBackButton = true,
        )
        AppBar(
            title = "Title",
            showBackButton = true,
        )
        AppBar(
            title = "Title",
            caption = "Caption",
            showBackButton = false,
        )
        AppBar(
            title = "Title",
            showBackButton = true,
        )
        AppBar(
            showBackButton = true,
        )
        AppBar(
            title = "Title",
            showBackButton = false,
        )
    }
}