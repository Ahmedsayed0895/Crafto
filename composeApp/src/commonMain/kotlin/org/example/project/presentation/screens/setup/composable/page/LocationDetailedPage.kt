package org.example.project.presentation.screens.setup.composable.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.enter_detailed_location
import org.example.project.presentation.components.DetailLocationInput
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun LocationDetailedPage(
    detailedLocation: String,
    locationDisplayText: String,
    onDetailedLocationChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(Res.string.enter_detailed_location),
            style = AppTheme.textStyle.title.small,
            color = AppTheme.craftoColors.shade.primary
        )

        Text(
            text = stringResource(Res.string.enter_detailed_location),
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.shade.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (locationDisplayText.isNotBlank() && locationDisplayText != "Select Location") {
            Text(
                text = locationDisplayText,
                style = AppTheme.textStyle.body.largeMedium,
                color = AppTheme.craftoColors.brand.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        DetailLocationInput(
            text = detailedLocation,
            onTextChange = onDetailedLocationChanged,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}