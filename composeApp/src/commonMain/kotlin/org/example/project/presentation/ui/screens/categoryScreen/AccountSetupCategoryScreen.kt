package org.example.project.presentation.ui.screens.categoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.account_setup_craftsman_category_description
import crafto.composeapp.generated.resources.account_setup_craftsman_category_title
import crafto.composeapp.generated.resources.account_setup_customer_category_description
import crafto.composeapp.generated.resources.account_setup_customer_category_title
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.ui.screens.categoryScreen.component.AccountSetupTopBar
import org.example.project.presentation.ui.screens.categoryScreen.component.ActionBox
import org.example.project.presentation.ui.screens.categoryScreen.component.TitleDescriptionBox
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupState
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AccountSetupCategoryScreen(
    viewModel: AccountSetupViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()
    AccountSetupCategoryContent(
        state = state,
        isCustomer = true,
        onBackButtonClick = {},
        onNextButtonClick = {},
        onChipSelected = viewModel::onCategorySelected
    )
}

@Composable
fun AccountSetupCategoryContent(
    modifier: Modifier = Modifier,
    state: AccountSetupState,
    isCustomer: Boolean,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onChipSelected: (id: Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        AccountSetupTopBar(onBackButtonClick = onBackButtonClick)
        TitleDescriptionBox(
            modifier = Modifier.weight(7f),
            title = selectCustomerOrCraftsmanText(isCustomer = isCustomer).first,
            description = selectCustomerOrCraftsmanText(isCustomer = isCustomer).second,
        )
        ActionBox(
            state = state,
            onChipSelected = onChipSelected,
        )
        PrimaryButton(
            text = "Next",
            enabled = true,
            buttonState = ButtonState.Enable,
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextButtonClick
        )

    }
}

@Composable
private fun selectCustomerOrCraftsmanText(isCustomer: Boolean): Pair<String, String> {
    return if (isCustomer) {
        stringResource(Res.string.account_setup_customer_category_title) to
                stringResource(Res.string.account_setup_customer_category_description)
    } else {
        stringResource(Res.string.account_setup_craftsman_category_title) to
                stringResource(Res.string.account_setup_craftsman_category_description)
    }
}




