package org.example.project.presentation.ui.screens.setupScreens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.account_setup_craftsman_category_description
import crafto.composeapp.generated.resources.account_setup_craftsman_category_title
import crafto.composeapp.generated.resources.account_setup_customer_category_description
import crafto.composeapp.generated.resources.account_setup_customer_category_title
//import org.example.project.presentation.ui.screens.setupScreens.component.CategoryActionBox
import org.example.project.presentation.ui.screens.setupScreens.component.SetupScreenScaffold
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
        currentPageNumber = 2,
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
    currentPageNumber: Int,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    onChipSelected: (id: Int) -> Unit,
) {
    SetupScreenScaffold(
        modifier = modifier,
        currentPageNumber = currentPageNumber,
        title = selectCustomerOrCraftsmanText(isCustomer).first,
        description = selectCustomerOrCraftsmanText(isCustomer).second,
        onBackButtonClick = onBackButtonClick,
        onNextButtonClick = onNextButtonClick,
    ) {
//        CategoryActionBox(
//            state = state,
//            onChipSelected = onChipSelected,
//        )
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




