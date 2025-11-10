package org.example.project.presentation.screens.setupScreens.location

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.*
import org.example.project.presentation.components.DetailLocationInput
import org.example.project.presentation.components.DropdownBottomSheet
import org.example.project.presentation.components.DropdownSelector
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun LocationSetupScreen(
    viewModel: LocationViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    HandleEffects(viewModel)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .background(AppTheme.craftoColors.background.screen)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AccountSetupTopBar(modifier = Modifier.fillMaxWidth(), currentPage = 2)
        LocationHeader(modifier = Modifier.weight(1f))

        DropdownSelector(
            text = state.locationDisplayText,
            hint = stringResource(Res.string.location_hint),
            icon = Res.drawable.location,
            hasSelection = state.selectedGovernorate.isNotBlank() && state.selectedDistrict.isNotBlank(),
            onClick = {
                viewModel.openGovernorateSheet()
            }
        )

        DetailLocationInput(
            text = state.detailLocation,
            onTextChange = viewModel::updateDetailLocation
        )

        ErrorMessage(error = state.error)

        NextButton(
            onClick = viewModel::onNextClick,
            enabled = state.selectedGovernorate.isNotBlank() && state.selectedDistrict.isNotBlank()
        )
    }

    DropdownBottomSheet(
        show = state.showGovernorateSheet,
        items = state.governorates,
        itemLabel = { it.name },
        onDismiss = viewModel::closeGovernorateSheet,
        onSelect = viewModel::selectGovernorate
    )

    DropdownBottomSheet(
        show = state.showDistrictSheet,
        items = state.districts,
        itemLabel = { it.name },
        onDismiss = viewModel::closeDistrictSheet,
        onSelect = { viewModel.selectDistrict(it.name) }
    )
}

@Composable
private fun HandleEffects(viewModel: LocationViewModel) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LocationEffect.NavigateToNextScreen -> {
                    // TODO
                }
                is LocationEffect.ShowError -> {
                    // TODO
                }
            }
        }
    }
}

@Composable
private fun LocationHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
    ) {
        Text(
            text = stringResource(Res.string.location_title),
            style = AppTheme.textStyle.display,
            color = AppTheme.craftoColors.shade.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = stringResource(Res.string.location_description),
            style = AppTheme.textStyle.body.largeRegular,
            color = AppTheme.craftoColors.shade.secondary
        )
    }
}



@Composable
private fun ErrorMessage(error: String?) {
    if (error != null) {
        Text(
            text = error,
            style = AppTheme.textStyle.body.medium,
            color = AppTheme.craftoColors.shade.quinary,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun NextButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    PrimaryButton(
        text = stringResource(Res.string.next_button),
        enabled = enabled,
        onClick = onClick,
        buttonState = if (enabled) ButtonState.Enable else ButtonState.DISABLED,
        modifier = Modifier.fillMaxWidth()
    )
}


@Preview
@Composable
private fun LocationSetupScreenPreview() {
    AppTheme(isDarkTheme = false) {
        LocationSetupScreen()
    }
}