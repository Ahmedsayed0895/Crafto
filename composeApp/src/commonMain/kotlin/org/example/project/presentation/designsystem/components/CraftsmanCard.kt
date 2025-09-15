package org.example.project.presentation.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.check_mark
import crafto.composeapp.generated.resources.clipboard_text
import crafto.composeapp.generated.resources.dialog
import crafto.composeapp.generated.resources.selection_card_img
import crafto.composeapp.generated.resources.star
import crafto.composeapp.generated.resources.star_1
import crafto.composeapp.generated.resources.user_rounded
import crafto.composeapp.generated.resources.verified_check_1
import org.example.project.presentation.designsystem.textstyle.AppTheme
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
    buttonText: String,
    buttonIcon: Painter? = null,
    buttonColors: ButtonColors,
    onButtonClick: () -> Unit = {}
) {
    val craftsmanIcon = craftsmanImage ?: painterResource(Res.drawable.user_rounded)

    val (textStyle, textColor) =
        if (craftsmanImage != null)
            AppTheme.textStyle.body.medium to AppTheme.craftoColors.shade.primary
        else
            AppTheme.textStyle.body.smallMedium to AppTheme.craftoColors.shade.secondary

    val (secondaryIcon, secondaryIconTint) =
        if (showOffers)
            painterResource(Res.drawable.clipboard_text) to AppTheme.craftoColors.brand.primary
        else
            painterResource(Res.drawable.star_1) to AppTheme.craftoColors.additional.primaryYellow

    val (secondaryText, secondaryTextColor) =
        if (showOffers)
            "$numberOfOffers Offers" to AppTheme.craftoColors.brand.primary
        else
            "$rating Rating" to AppTheme.craftoColors.shade.secondary


    Row(
        modifier = modifier.fillMaxWidth().background(AppTheme.craftoColors.background.card),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        if (craftsmanImage == null) {
            DashedCircle(
                modifier = Modifier.clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                    .size(40.dp),
                icon = craftsmanIcon,
                dashedLineColor = AppTheme.craftoColors.shade.quaternary,
                tintColor = AppTheme.craftoColors.shade.tertiary
            )
        } else {
            CircleAvatar(
                modifier = Modifier.clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                    .size(40.dp),
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

        Spacer(modifier = Modifier.weight(1f))

        DefaultButton(
            text = buttonText,
            enabled = true,
            onClick = onButtonClick,
            icon = buttonIcon,
            colors = buttonColors,
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp)
        )
    }
}

@Composable
private fun CircleAvatar(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(AppTheme.craftoRadius.full))
            .size(55.dp)
            .background(Color.Transparent)
    ) {
        Box(modifier = modifier.align(Alignment.Center)) {
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

@Composable
private fun DashedCircle(
    icon: Painter,
    dashedLineColor: Color,
    tintColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .drawBehind {
                drawRoundRect(
                    color = dashedLineColor,
                    style = Stroke(
                        width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 10f), 0f)
                    ),
                    cornerRadius = CornerRadius(size.minDimension / 2)
                )
            }
    ) {
        Icon(
            painter = icon,
            contentDescription = "icon",
            tint = tintColor,
            modifier = Modifier.align(Alignment.Center).size(20.dp)
        )
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
                craftsmanImage = painterResource(Res.drawable.selection_card_img),
                buttonText = "Rate",
                buttonIcon = painterResource(Res.drawable.star),
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.shade.quinary,
                    contentColor = AppTheme.craftoColors.button.onSecondary
                )
            )

            CraftsmanCard(
                modifier = Modifier.padding(16.dp),
                craftsmanName = "Craftsman Not Chosen",
                numberOfOffers = 3,
                showOffers = true,
                buttonText = "View Offers",
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.shade.quinary,
                    contentColor = AppTheme.craftoColors.button.onSecondary
                )
            )
            CraftsmanCard(
                modifier = Modifier.padding(16.dp),
                craftsmanName = "Hend",
                rating = 4.5,
                showOffers = false,
                craftsmanImage = painterResource(Res.drawable.selection_card_img),
                buttonText = "Chat",
                buttonIcon = painterResource(Res.drawable.dialog),
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.shade.quinary,
                    contentColor = AppTheme.craftoColors.button.onSecondary
                )
            )
        }
    }
}