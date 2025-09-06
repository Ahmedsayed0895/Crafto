package org.example.project.designSystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.craftman_avatar
import crafto.composeapp.generated.resources.star
import crafto.composeapp.generated.resources.star_1
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class OfferCardState{
    ACCEPTED, REJECTED, WAITING
}
@Composable
fun OfferCard(
    cardState: OfferCardState = OfferCardState.WAITING,
    modifier: Modifier = Modifier,
    name: String,
    rating: Double,
    reviews: Int,
    hour: Int,
    offerDescription: String,
) {
  Column (
      modifier
          .background(Color.White)
          .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
  )
  {
      CardHeader(
          name = name,
          rating = rating,
          reviews = reviews,
          hour = hour,
      )
      Text(
          text = offerDescription,
          style = AppTheme.textStyle.body.smallMedium,
          color = AppTheme.craftoColors.shade.primary
      )
      Row {

      }


    }
  }

@Composable
private fun CardHeader(
    modifier: Modifier = Modifier,
    name: String,
    rating: Double,
    reviews: Int,
    hour: Int, )
{
    Row (
        modifier = modifier
            .fillMaxWidth()
    )
    {
        Image(
            painter = painterResource(Res.drawable.craftman_avatar),
            contentDescription = "Offer Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
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
)
{
    Column (
        modifier = modifier.padding(start = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween

    ){
        Text(
            text = name,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.shade.primary)
        Row (
            modifier = Modifier.padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
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
fun OfferCardPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        OfferCard(
            name = "Muhammed Ali",
            rating = 4.7,
            reviews = 121,
            hour = 5,
            offerDescription = "I can fix this today." +
                    " I have 10+ years experience with kitchen plumbing."
        )
    }
}