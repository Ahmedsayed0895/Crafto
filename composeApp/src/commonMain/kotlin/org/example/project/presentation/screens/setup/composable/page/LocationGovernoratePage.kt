package org.example.project.presentation.screens.setup.composable.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.choose_governorate
import crafto.composeapp.generated.resources.location
import org.example.project.presentation.components.DropdownSelector
import org.example.project.presentation.designsystem.components.CraftoCircularProgressIndicator
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.model.GovernoratesUiModel
import org.jetbrains.compose.resources.stringResource

@Composable
fun LocationGovernoratePage(
    selectedGovernorate: GovernoratesUiModel?,
    locationDisplayText: String,
    isLoading: Boolean,
    onOpenSheet: () -> Unit,
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
            text = stringResource(Res.string.choose_governorate),
            style = AppTheme.textStyle.title.small,
            color = AppTheme.craftoColors.shade.primary
        )

        Text(
            text = "",
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.shade.secondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CraftoCircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                )
            }
        } else {
            DropdownSelector(
                text = selectedGovernorate?.name ?: "",
                hint = "",
                icon = Res.drawable.location,
                hasSelection = selectedGovernorate != null,
                onClick = onOpenSheet,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }

}