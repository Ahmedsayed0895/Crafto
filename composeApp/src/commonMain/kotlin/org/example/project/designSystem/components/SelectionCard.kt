package org.example.project.designSystem.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.selection_card_img
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SelectionCard(
    img: Painter,
    title: String,
    caption: String,
    isSelected: Boolean,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background by animateColorAsState(
        if (isSelected) AppTheme.craftoColors.brand.tertiary else AppTheme.craftoColors.background.card
    )
    val strokeColor by animateColorAsState(
        if (isSelected) AppTheme.craftoColors.brand.secondary else Color.Transparent
    )
    val titleColor by animateColorAsState(
        if (isSelected) AppTheme.craftoColors.brand.primary else AppTheme.craftoColors.shade.primary
    )

    Card(
        onClick = onCardClick,
        modifier.border(1.dp, strokeColor, RoundedCornerShape(AppTheme.craftoRadius.xl)),
        shape = RoundedCornerShape(AppTheme.craftoRadius.xl),
        colors = CardDefaults.cardColors(
            background
        ),
    ) {
        Image(
            painter = img,
            contentDescription = null,
            modifier = Modifier.padding(12.dp)
        )

        Text(
            text = title,
            style = AppTheme.textStyle.body.mediumSemiBold,
            modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally),
            color = titleColor

        )
        Text(
            text = caption,
            style = AppTheme.textStyle.body.smallMedium,
            color = AppTheme.craftoColors.shade.secondary,
            modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally)
        )

    }
}

@Preview
@Composable
private fun SelectionCardPreview() {
    SelectionCard(
        img = painterResource(Res.drawable.selection_card_img),
        title = "Title",
        caption = "Caption",
        isSelected = true,
        onCardClick = {},
        modifier = Modifier
    )
}