package org.example.project.presentation.ui.screens.categoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.account_setup_craftsman_category_description
import crafto.composeapp.generated.resources.account_setup_craftsman_category_title
import crafto.composeapp.generated.resources.account_setup_customer_category_description
import crafto.composeapp.generated.resources.account_setup_customer_category_title
import crafto.composeapp.generated.resources.arrow_left
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.Chip
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.components.ProgressIndicator
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupState
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

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
@Composable
private fun AccountSetupTopBar(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit

) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        BackButton(onBackButtonClick = onBackButtonClick)
        ProgressIndicator(
            currentPage = 2,
            totalPage = 4,
            modifier = Modifier.fillMaxWidth(0.75f),
        )
    }
}

@Composable
private fun TitleDescriptionBox(
    modifier: Modifier,
    title: String,
    description: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(), contentAlignment = Alignment.BottomStart

    ) {
        TitleDescriptionText(
            title = title,
            description = description
        )

    }
}

@Composable
private fun TitleDescriptionText(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        Text(
            text = title,
            style = AppTheme.textStyle.display,
            color = AppTheme.craftoColors.shade.primary
        )
        Text(
            text = description,
            style = AppTheme.textStyle.body.largeRegular,
            color = AppTheme.craftoColors.shade.secondary
        )
    }
}


@Composable
private fun ActionBox(
    modifier: Modifier = Modifier,
    state: AccountSetupState,
    onChipSelected: (id: Int) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            state.categoryState.categories.forEachIndexed { index, category ->
                Chip(
                    text = category.title,
                    isSelected = category.isSelected,
                    onChipSelected = { onChipSelected(category.id) },
                    modifier = Modifier.background(
                        if (category.isSelected)
                            category.color
                        else
                            AppTheme.craftoColors.background.card,
                        shape = RoundedCornerShape(AppTheme.craftoRadius.full)
                    ),
                    textColor = if (category.isSelected)
                        AppTheme.craftoColors.background.card
                    else
                        AppTheme.craftoColors.shade.secondary
                )
            }
        }
    }
}


@Composable
private fun BackButton(
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(AppTheme.craftoColors.background.card)
            .clickable(onClick = onBackButtonClick),
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            painter = painterResource(Res.drawable.arrow_left),
            contentDescription = "back button",
            tint = AppTheme.craftoColors.shade.primary
        )
    }
}


@Preview
@Composable
fun AccCategoryLightPreview() {
    AppTheme {
        AccountSetupCategoryScreen()

    }
}

@Preview
@Composable
fun AccCategoryDarkPreview() {
    AppTheme(isDarkTheme = true) {
        AccountSetupCategoryScreen()

    }
}