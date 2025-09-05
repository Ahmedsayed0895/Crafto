package org.example.project.designSystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.clipboard_text
import crafto.composeapp.generated.resources.selection_card_img
import crafto.composeapp.generated.resources.star_1
import crafto.composeapp.generated.resources.user_rounded
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CraftsmanCard(
    modifier: Modifier = Modifier,
    craftsmanImage: Painter? = null,
    craftsmanName: String = "",
    rating: Double = 0.0,
    showOffers: Boolean = true,
    numberOfOffers: Int = 0,
    button: @Composable () -> Unit = {}
) {
    val craftsmanIcon = craftsmanImage ?: painterResource(Res.drawable.user_rounded)
    val textStyle =
        if (craftsmanImage != null) AppTheme.textStyle.body.medium else AppTheme.textStyle.body.smallMedium
    val textColor =
        if (craftsmanImage != null) AppTheme.craftoColors.shade.primary else AppTheme.craftoColors.shade.secondary
    val secondaryIcon =
        if (showOffers) painterResource(Res.drawable.clipboard_text) else painterResource(Res.drawable.star_1)
    val secondaryIconTint =
        if (showOffers) AppTheme.craftoColors.brand.primary else AppTheme.craftoColors.additional.primaryYellow
    val secondaryText = if (showOffers) "$numberOfOffers Offers" else "$rating Rating"
    val secondaryTextColor =
        if (showOffers) AppTheme.craftoColors.brand.primary else AppTheme.craftoColors.shade.secondary


    Row(
        modifier = modifier.fillMaxWidth().background(AppTheme.craftoColors.background.card),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        if(craftsmanImage==null){
            DashedCircle(
                modifier = Modifier.clip(RoundedCornerShape(AppTheme.craftoRadius.full)).size(40.dp),
                icon = craftsmanIcon,
                dashedLineColor = AppTheme.craftoColors.shade.quaternary,
                tintColor = AppTheme.craftoColors.shade.tertiary
            )
        }else{
            CircleAvatar(
                modifier =  Modifier.clip(RoundedCornerShape(AppTheme.craftoRadius.full)).size(40.dp),
                image = painterResource(Res.drawable.selection_card_img)
            )
        }

        Column(
            modifier = Modifier,
        ) {
            Text(
                text = craftsmanName,
                style = textStyle,
                color = textColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = secondaryIcon,
                    contentDescription = "secondary icon",
                    modifier = Modifier.size(16.dp),
                    tint = secondaryIconTint
                )

                Text(
                    text = secondaryText,
                    color = secondaryTextColor,
                    style = AppTheme.textStyle.body.smallMedium
                )
            }
        }

        // Buttons Here
    }
}

@Preview
@Composable
private fun CraftsmanCardPreview() {
    AppTheme(isDarkTheme = false) {
        Column(
            modifier = Modifier.background(AppTheme.craftoColors.background.card),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CraftsmanCard(
                modifier = Modifier.padding(16.dp),
                craftsmanName = "Hend",
                rating = 4.5,
                showOffers = false,
                craftsmanImage = painterResource(Res.drawable.selection_card_img)
            )

            CraftsmanCard(
                modifier = Modifier.padding(16.dp),
                craftsmanName = "Craftsman Not Chosen",
                numberOfOffers = 3,
                showOffers = true,
            )
        }
    }
}