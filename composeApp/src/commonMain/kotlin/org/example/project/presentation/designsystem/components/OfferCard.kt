package org.example.project.presentation.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.accept_offer
import crafto.composeapp.generated.resources.chat
import crafto.composeapp.generated.resources.clock_circle
import crafto.composeapp.generated.resources.craftman_avatar
import crafto.composeapp.generated.resources.star_1
import crafto.composeapp.generated.resources.verified_check_1
import crafto.composeapp.generated.resources.wallet
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class OfferCardState {
    ACCEPTED, REJECTED, WAITING
}

@Composable
fun OfferCard(
    offerDardState: OfferCardState = OfferCardState.WAITING,
    modifier: Modifier = Modifier,
    name: String,
    image: DrawableResource,
    rating: Double,
    reviews: Int,
    offerCreationTime: Int,
    offerDescription: String,
    fees: Double,
    visitDate: String,
    visitTime: String,
    onChatClick: () -> Unit,
    onAcceptClick: () -> Unit,
) {
    when (offerDardState) {
        OfferCardState.ACCEPTED -> {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = AppTheme.craftoColors.additional.primarySuccess,
                        shape = RoundedCornerShape(AppTheme.craftoRadius.lg)
                    )
                    .clip(RoundedCornerShape(AppTheme.craftoRadius.xl)),

                )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.craftoColors.additional.primarySuccess),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.dp),
                        text = "Offer Accepted",
                        style = AppTheme.textStyle.label.medium,
                        color = AppTheme.craftoColors.background.card
                    )

                }
                Column(
                    modifier = Modifier
                        .background(AppTheme.craftoColors.background.card)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )
                {
                    CardHeader(
                        name = name,
                        rating = rating,
                        reviews = reviews,
                        hour = offerCreationTime,
                        image = image
                    )
                    OfferBody(
                        fees = fees,
                        date = visitDate,
                        time = visitTime,
                        offerDescription = offerDescription
                    )
                }
            }
        }

        OfferCardState.REJECTED -> {
            Surface(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(AppTheme.craftoRadius.xl)),
            ) {
                Column(
                    modifier = Modifier
                        .background(AppTheme.craftoColors.background.card)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )
                {
                    CardHeader(
                        name = name,
                        rating = rating,
                        reviews = reviews,
                        hour = offerCreationTime,
                        image = image
                    )
                    OfferBody(
                        fees = fees,
                        date = visitDate,
                        time = visitTime,
                        offerDescription = offerDescription
                    )
                }
            }
        }

        OfferCardState.WAITING -> {
            Surface(
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(AppTheme.craftoRadius.xl)),
            ) {
                Column(
                    modifier = Modifier
                        .background(AppTheme.craftoColors.background.card)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )
                {
                    CardHeader(
                        name = name,
                        rating = rating,
                        reviews = reviews,
                        hour = offerCreationTime,
                        image = image
                    )
                    OfferBody(
                        fees = fees,
                        date = visitDate,
                        time = visitTime,
                        offerDescription = offerDescription
                    )
                    OfferButtons(onChatClick,onAcceptClick)
                }
            }
        }
    }
}


@Composable
private fun OfferButtons(
    onChatClick: () -> Unit,
    onAcceptClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        SecondaryButton(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp),
            text = stringResource(Res.string.chat),
            enabled = true,
            buttonState = ButtonState.Enable,
            containerColor = AppTheme.craftoColors.shade.quaternary,
            onClick = { onChatClick() },
        )
        PrimaryButton(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.accept_offer),
            enabled = true,
            buttonState = ButtonState.Enable,
            onClick = { onAcceptClick() },
        )

    }
}

@Composable
private fun OfferBody(
    modifier: Modifier = Modifier,
    offerDescription: String,
    fees: Double,
    date: String,
    time: String
) {
    Text(
        text = offerDescription,
        style = AppTheme.textStyle.body.smallMedium,
        color = AppTheme.craftoColors.shade.primary
    )
    OfferDetails(modifier = modifier, fees = fees, date = date, time = time)
}

@Composable
private fun OfferDetails(
    modifier: Modifier = Modifier,
    fees: Double,
    date: String,
    time: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)

    )
    {
        Icon(
            modifier = Modifier
                .size(16.dp),
            painter = painterResource(Res.drawable.wallet),
            contentDescription = "wallet Icon",
            tint = AppTheme.craftoColors.shade.secondary,
        )
        Text(
            text = fees.toString(),
            style = AppTheme.textStyle.body.smallMedium,
            color = AppTheme.craftoColors.shade.secondary,
        )
        Canvas(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .size(3.dp)
        ) {
            drawCircle(
                color = Color.LightGray
            )
        }
        Icon(
            modifier = Modifier
                .size(16.dp),
            painter = painterResource(Res.drawable.clock_circle),
            contentDescription = "wallet Icon",
            tint = AppTheme.craftoColors.shade.secondary,
        )
        Text(
            text = "$date, $time",
            style = AppTheme.textStyle.body.smallMedium,
            color = AppTheme.craftoColors.shade.secondary,
        )


    }

}

@Composable
private fun CardHeader(
    modifier: Modifier = Modifier,
    name: String,
    image: DrawableResource,
    rating: Double,
    reviews: Int,
    hour: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    )
    {
        Box(
            modifier = Modifier
                .size(48.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = "Offer Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.BottomCenter),
                painter = painterResource(Res.drawable.verified_check_1),
                tint = AppTheme.craftoColors.additional.primarySuccess,
                contentDescription = "verified Icon",
            )

        }
        CraftsManInfo(
            name = name,
            rating = rating,
            reviews = reviews
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "$hour" + "h ago",
            style = AppTheme.textStyle.body.smallRegular,
            color = AppTheme.craftoColors.shade.tertiary
        )
    }
}

@Composable
private fun CraftsManInfo(
    modifier: Modifier = Modifier,
    name: String,
    rating: Double,
    reviews: Int,
) {
    Column(
        modifier = modifier.padding(start = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = name,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.shade.primary
        )
        Row(
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 4.dp),
                painter = painterResource(Res.drawable.star_1),
                contentDescription = "Star Icon",
                tint = AppTheme.craftoColors.additional.primaryWarning,
            )

            Text(
                text = "$rating ($reviews reviews)",
                style = AppTheme.textStyle.body.smallMedium,
                color = AppTheme.craftoColors.shade.secondary
            )
        }

    }

}


@Preview
@Composable
fun OfferCardPreview_waiting() {
    AppTheme {
        OfferCard(
            name = "Muhammed Ali",
            rating = 4.7,
            reviews = 121,
            offerCreationTime = 5,
            offerDescription = "I can fix this today." +
                    " I have 10+ years experience with kitchen plumbing.",
            fees = 200.0,
            visitDate = "Tomorrow",
            visitTime = "2:00 PM",
            offerDardState = OfferCardState.WAITING,
            onChatClick = {},
            onAcceptClick = {},
            image = Res.drawable.craftman_avatar
        )
    }
}

@Preview
@Composable
fun OfferCardPreview_accepted() {
    AppTheme {
        OfferCard(
            name = "Muhammed Ali",
            rating = 4.7,
            reviews = 121,
            offerCreationTime = 5,
            offerDescription = "I can fix this today." +
                    " I have 10+ years experience with kitchen plumbing.",
            fees = 200.0,
            visitDate = "Tomorrow",
            visitTime = "2:00 PM",
            offerDardState = OfferCardState.ACCEPTED,
            onChatClick = {},
            onAcceptClick = {},
            image = Res.drawable.craftman_avatar
        )
    }
}

@Preview
@Composable
fun OfferCardPreview_rejected() {
    AppTheme {
        OfferCard(
            name = "Muhammed Ali",
            rating = 4.7,
            reviews = 121,
            offerCreationTime = 5,
            offerDescription = "I can fix this today." +
                    " I have 10+ years experience with kitchen plumbing.",
            fees = 200.0,
            visitDate = "Tomorrow",
            visitTime = "2:00 PM",
            offerDardState = OfferCardState.REJECTED,
            onChatClick = {},
            onAcceptClick = {},
            image = Res.drawable.craftman_avatar
        )
    }
}