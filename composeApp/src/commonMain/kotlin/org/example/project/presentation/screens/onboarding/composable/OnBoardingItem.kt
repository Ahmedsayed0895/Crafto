package org.example.project.presentation.screens.onboarding.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.onboarding.model.OnboardingUiState


@Composable
fun OnBoardingItem(
    page: OnboardingUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(AppTheme.craftoColors.background.screen)
    ) {

        Box(
            modifier = Modifier
                .background(
                shape = RoundedCornerShape(AppTheme.craftoRadius.x5l),
                color = Color.Transparent
            ).padding(bottom = 32.dp).height(335.dp)

        ) {
            AsyncImage(
                model = page.imageRes,
                contentDescription = "OnBoarding Image",
                contentScale =  ContentScale.FillBounds
            )
        }

        Text(
            text = page.title,
            style = AppTheme.textStyle.display,
            color = AppTheme.craftoColors.shade.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = page.description,
            style = AppTheme.textStyle.body.largeRegular,
            color = AppTheme.craftoColors.shade.secondary,
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
}