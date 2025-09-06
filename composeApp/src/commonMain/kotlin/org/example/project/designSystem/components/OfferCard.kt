package org.example.project.designSystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.allDrawableResources
import crafto.composeapp.generated.resources.craftman_avatar
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class OfferCardState{
    ACCEPTED, REJECTED, WAITING
}
@Composable
fun OfferCard(
    cardState: OfferCardState = OfferCardState.WAITING,
    modifier: Modifier = Modifier
) {
  Column (
      modifier
          .background(Color.White)
  )
  {
      Row (
          modifier = Modifier
              .fillMaxWidth()
      )
      {
            Image(
                painter = painterResource(Res.drawable.craftman_avatar),
                contentDescription = "Offer Image",
                modifier = Modifier
                    .fillMaxSize()
            )

      }

  }

}


@Preview
@Composable
fun OfferCardPreview() {
    OfferCard()
}