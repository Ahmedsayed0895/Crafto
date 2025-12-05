package org.example.project.presentation.screens.customer_home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.arrow_left
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.customer_home.CustomerHomeScreenState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MostRequestItem(
    category: CustomerHomeScreenState.RequestCategoryUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = AppTheme.craftoColors.background.card,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = category.iconBackgroundColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(10.dp),
            painter = painterResource(Res.drawable.arrow_left),
            contentDescription = null,
            tint = category.iconTint
        )

        Text(
            modifier = Modifier.padding(top = 12.dp, bottom = 2.dp),
            text = category.title,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.shade.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = category.content,
            style = AppTheme.textStyle.label.mediumRegular,
            color = AppTheme.craftoColors.shade.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
private fun MostRequestItemPreview(){
    AppTheme{
        MostRequestItem(
            category = CustomerHomeScreenState.RequestCategoryUiState(
                title = "Plumping",
                content = "Pipes, faucets, water heaters",
                icon = painterResource(Res.drawable.arrow_left),
                iconBackgroundColor = AppTheme.craftoColors.additional.secondaryBlue,
                iconTint = AppTheme.craftoColors.additional.primaryBlue
            )
        )
    }
}