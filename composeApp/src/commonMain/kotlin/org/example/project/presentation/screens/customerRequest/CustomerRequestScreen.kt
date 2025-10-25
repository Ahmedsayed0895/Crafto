package org.example.project.presentation.screens.customerRequest

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.dialog
import crafto.composeapp.generated.resources.notifications
import crafto.composeapp.generated.resources.star
import org.example.project.presentation.designsystem.components.CategoryItemHorizontal
import org.example.project.presentation.designsystem.components.CraftsmanCard
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.customerRequest.component.CircularProgressLoading
import org.example.project.presentation.screens.customerRequest.component.DividedLine
import org.example.project.presentation.screens.customerRequest.component.NoServiceRequestPlaceholder
import org.example.project.presentation.screens.customerRequest.component.RateBottomSheet
import org.example.project.presentation.screens.customerRequest.component.RequestsTabs
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun CustomerRequestScreen(
    viewModel: CustomerRequestViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    AnimatedContent(
        targetState = state.isLoading,
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "CustomerRequestScreen"
    ) {
        if (it) {
            CircularProgressLoading()
        } else {
            CustomerRequestContent(listener = viewModel, state = state)
        }
    }
}


@Composable
private fun CustomerRequestContent(
    listener: CustomerRequestInteractionListener,
    state: CustomerRequestScreenUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
    ) {
        AppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.craftoColors.background.card)
                .statusBarsPadding()
                .padding(vertical = 18.dp, horizontal = 16.dp)
        )

        AnimatedContent(
            targetState = state.listOfRequests,
            transitionSpec = { fadeIn() togetherWith fadeOut() }
        ) { requests ->
            if (requests.isEmpty()) {
                NoServiceRequestPlaceholder(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 40.dp)
                )
            } else {
                LazyColumn {
                    stickyHeader {
                        RequestsTabs(
                            state = state,
                            onTabSelected = { listener.onTabClicked(it) }
                        )
                    }

                    when (state.selectedTapIndex) {
                        CustomerRequestScreenUiState.RequestsTab.ONGOING -> {
                            val ongoingRequests = state.listOfRequests.filter {
                                it.requestStatus == CustomerRequestScreenUiState.RequestsTab.ONGOING
                            }
                            requestOngoingList(ongoingRequests)
                        }

                        CustomerRequestScreenUiState.RequestsTab.COMPLETED -> {
                            val completedRequests = state.listOfRequests.filter {
                                it.requestStatus == CustomerRequestScreenUiState.RequestsTab.COMPLETED
                            }
                            requestCompleteList(completedRequests)
                        }

                        CustomerRequestScreenUiState.RequestsTab.CANCELED -> {
                            val cancelledRequests = state.listOfRequests.filter {
                                it.requestStatus == CustomerRequestScreenUiState.RequestsTab.CANCELED
                            }
                            requestCancelList(cancelledRequests, listener)
                        }
                    }

                }
            }
        }
    }

    AnimatedVisibility(state.isBottomSheetVisible) {
        RateBottomSheet(
            updateRate = state.numberOfRating,
            onDismiss = listener::onDismissBottomSheet,
            onClick = listener::onBottomSheetConfirm
        )
    }
}

private fun LazyListScope.requestOngoingList(state: List<CustomerRequestScreenUiState.CustomerRequestUiState>) {
    items(state) { request ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.craftoColors.background.card)
                .padding(16.dp)
        ) {
            CategoryItemHorizontal(
                icon = painterResource(Res.drawable.star),
                title = request.issueCategory.title,
                description = request.issueDescription,
                onClick = {}
            )
            DividedLine()
            CraftsmanCard(
                showOffers = true,
                craftsmanName = "Craftsman Not Chosen",
                numberOfOffers = 3,
                buttonText = "View Offers",
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.shade.quinary,
                    contentColor = AppTheme.craftoColors.button.onSecondary
                ),
                onButtonClick = {}
            )
        }
    }
}

private fun LazyListScope.requestCompleteList(state: List<CustomerRequestScreenUiState.CustomerRequestUiState>) {
    items(state) { request ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.craftoColors.background.card)
                .padding(16.dp)
        ) {
            CategoryItemHorizontal(
                icon = painterResource(Res.drawable.star),
                title = request.issueCategory.title,
                description = request.issueDescription,
                onClick = {}
            )
            DividedLine()
            CraftsmanCard(
                rating = 5.0,
                craftsmanName = "",
                buttonText = "Chat",
                buttonIcon = painterResource(Res.drawable.dialog),
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.shade.quinary,
                    contentColor = AppTheme.craftoColors.button.onSecondary
                ),
                onButtonClick = {}
            )
        }
    }
}

private fun LazyListScope.requestCancelList(
    state: List<CustomerRequestScreenUiState.CustomerRequestUiState>,
    listener: CustomerRequestInteractionListener

) {
    items(state) { request ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.craftoColors.background.card)
                .padding(16.dp)
        ) {
            CategoryItemHorizontal(
                icon = painterResource(Res.drawable.star),
                title = request.issueCategory.title,
                description = request.issueDescription,
                onClick = {}
            )
            DividedLine()
            CraftsmanCard(
                rating = 5.0,
                buttonText = "Rate",
                buttonIcon = painterResource(Res.drawable.star),
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.craftoColors.shade.quinary,
                    contentColor = AppTheme.craftoColors.button.onSecondary
                ),
                onButtonClick = { listener::onRateClicked }
            )
        }
    }
}

@Composable
private fun AppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "My Requests",
            style = AppTheme.textStyle.title.small,
            color = AppTheme.craftoColors.shade.primary
        )
        Icon(
            painter = painterResource(Res.drawable.notifications),
            contentDescription = "notification",
            tint = AppTheme.craftoColors.shade.primary,
        )
    }
}