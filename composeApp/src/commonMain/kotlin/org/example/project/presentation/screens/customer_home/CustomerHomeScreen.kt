package org.example.project.presentation.screens.customer_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.arrow_left
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.customer_home.composable.CustomerHomeAppBar
import org.example.project.presentation.screens.customer_home.composable.MostRequestItem
import org.example.project.presentation.screens.customer_home.composable.WhatYouNeedItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CustomerHomeScreen(
    viewModel: CustomerHomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CustomerHomeScreenContent(
        state = state,
        interactionListener = viewModel
    )
}

@Composable
private fun CustomerHomeScreenContent(
    state: CustomerHomeScreenState,
    interactionListener: CustomerHomeInteractionListener
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen),
        topBar = {
            CustomerHomeAppBar(
                customerName = state.customer.name,
                customerLocation = state.customer.location,
                onNotificationIconClicked = interactionListener::onNotificationIconClicked,
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.craftoColors.background.screen),
            contentPadding = innerPadding
        ) {
            item {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
                    text = "Most Requested",
                    style = AppTheme.textStyle.title.small,
                    color = AppTheme.craftoColors.shade.primary,
                )
            }

            item {
                LazyRow(modifier = Modifier.padding(bottom = 24.dp)) {
                    items(state.mostRequestedCategories) { category ->
                        MostRequestItem(
                            modifier = Modifier.padding(start = 12.dp),
                            category = category
                        )
                    }
                }
            }

            item {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp, start = 16.dp),
                    text = "Find What You Need",
                    style = AppTheme.textStyle.title.small,
                    color = AppTheme.craftoColors.shade.primary,
                )
            }

            items(state.allCategories) { category ->
                WhatYouNeedItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 12.dp),
                    category = category
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomerHomeScreenPreview(){
    val categories = listOf(
        CustomerHomeScreenState.RequestCategoryUiState(
            title = "Plumping",
            content = "Pipes, faucets, water heaters",
            icon = painterResource(Res.drawable.arrow_left),
            iconBackgroundColor = AppTheme.craftoColors.additional.secondaryBlue,
            iconTint = AppTheme.craftoColors.additional.primaryBlue
        ),
        CustomerHomeScreenState.RequestCategoryUiState(
            title = "Plumping",
            content = "Pipes, faucets, water heaters",
            icon = painterResource(Res.drawable.arrow_left),
            iconBackgroundColor = AppTheme.craftoColors.additional.secondaryBlue,
            iconTint = AppTheme.craftoColors.additional.primaryBlue
        ),
        CustomerHomeScreenState.RequestCategoryUiState(
            title = "Plumping",
            content = "Pipes, faucets, water heaters",
            icon = painterResource(Res.drawable.arrow_left),
            iconBackgroundColor = AppTheme.craftoColors.additional.secondaryBlue,
            iconTint = AppTheme.craftoColors.additional.primaryBlue
        )
    )
    AppTheme{
        CustomerHomeScreenContent(
            state = CustomerHomeScreenState(
                mostRequestedCategories = categories,
                allCategories = categories
            ),
            interactionListener = object : CustomerHomeInteractionListener{
                override fun onSearchClicked() {}
                override fun onRequestCategoryClicked() {}
                override fun onNotificationIconClicked() {}
            }
        )
    }
}