package org.example.project.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.alt_arrow_down
import crafto.composeapp.generated.resources.location
import org.example.project.presentation.designsystem.components.BottomSheet
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.components.TextField
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screen.AccountSetupTopBar
import org.example.project.presentation.screen.LocationEffect
import org.example.project.presentation.screen.LocationViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LocationSetupScreen(
    viewModel: LocationViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LocationEffect.NavigateToNextScreen -> {}
                is LocationEffect.ShowError -> {
                }
            }
        }
    }

    val locationParts = listOf(state.selectedGovernorate, state.selectedDistrict).filter { it.isNotBlank() }
    val displayText = if (locationParts.isEmpty()) "Governorate, District" else locationParts.joinToString(", ")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .padding(horizontal = 16.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AccountSetupTopBar(
            modifier = Modifier.fillMaxWidth(),
            currentPage = 2
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = "Where are you located?",
                style = AppTheme.textStyle.display,
                color = AppTheme.craftoColors.shade.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Location helps improve accuracy, but don't worry, you can update it later.",
                style = AppTheme.textStyle.body.largeRegular,
                color = AppTheme.craftoColors.shade.secondary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    color = AppTheme.craftoColors.background.card,
                    shape = RoundedCornerShape(AppTheme.craftoRadius.lg)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                hint = "Governorate, District",
                text = displayText,
                onTextChange = {},
                modifier = Modifier.fillMaxWidth(),
                startIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.location),
                        contentDescription = "Location icon",
                        tint = if (locationParts.isNotEmpty()) AppTheme.craftoColors.shade.primary else AppTheme.craftoColors.shade.tertiary
                    )
                },
                endIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.alt_arrow_down),
                        contentDescription = "Dropdown",
                        tint = if (locationParts.isNotEmpty()) AppTheme.craftoColors.shade.primary else AppTheme.craftoColors.shade.tertiary,
                        modifier = Modifier.clickable { viewModel.openGovernorateSheet() }
                    )
                }
            )
        }

        TextField(
            hint = "Enter your location in detail",
            text = state.detailLocation,
            onTextChange = viewModel::updateDetailLocation,
            modifier = Modifier.fillMaxWidth()
        )

        if (state.error != null) {
            Text(
                text = state.error ?: "",
                style = AppTheme.textStyle.body.medium,
                color = AppTheme.craftoColors.shade.quinary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        PrimaryButton(
            text = "Next",
            enabled = true,
            onClick = { viewModel.onNextClick() },
            buttonState = ButtonState.Enable,
            modifier = Modifier.fillMaxWidth()
        )
    }

    if (state.showGovernorateSheet) {
        BottomSheet(
            title = "Choose Governorate",
            onDismissRequest = viewModel::closeGovernorateSheet
        ) {
            state.governorates.forEach { governorate ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.selectGovernorate(governorate)
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = governorate.name,
                        style = AppTheme.textStyle.body.medium,
                        color = AppTheme.craftoColors.shade.primary,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(Res.drawable.alt_arrow_down),
                        contentDescription = "Arrow",
                        tint = AppTheme.craftoColors.shade.secondary
                    )
                }
            }
        }
    }

    if (state.showDistrictSheet) {
        BottomSheet(
            title = "Choose District",
            onDismissRequest = viewModel::closeDistrictSheet
        ) {
            state.districts.forEach { d ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.selectDistrict(d.name)
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = d.name,
                        style = AppTheme.textStyle.body.medium,
                        color = AppTheme.craftoColors.shade.primary,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LocationSetupScreenPreview() {
    AppTheme(isDarkTheme = false) {
        LocationSetupScreen()
    }
}