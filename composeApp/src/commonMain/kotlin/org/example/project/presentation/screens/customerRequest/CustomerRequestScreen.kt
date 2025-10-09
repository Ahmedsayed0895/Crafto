package org.example.project.presentation.screens.customerRequest

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.dialog
import crafto.composeapp.generated.resources.notifications
import crafto.composeapp.generated.resources.star
import org.example.project.presentation.designsystem.components.CategoryItemHorizontal
import org.example.project.presentation.designsystem.components.CraftsmanCard
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.customerRequest.component.NoServiceRequestPlaceholder
import org.example.project.presentation.screens.customerRequest.component.RateBottomSheet
import org.example.project.presentation.viewmodel.customerRequest.CustomerRequestInteractionListener
import org.example.project.presentation.viewmodel.customerRequest.CustomerRequestUiState
import org.example.project.presentation.viewmodel.customerRequest.CustomerRequestViewModel
import org.example.project.presentation.viewmodel.customerRequest.Tabs
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CustomerRequestScreen(
    viewModel: CustomerRequestViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    CustomerRequestContent(
        listener = viewModel,
        state = state
    )
}

@Composable
private fun CustomerRequestContent(
    listener: CustomerRequestInteractionListener,
    state: CustomerRequestUiState,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
    ) {
        AppBar(
            Modifier.fillMaxWidth()
                .background(AppTheme.craftoColors.background.card)
                .statusBarsPadding()
                .padding(vertical = 18.dp, horizontal = 16.dp)
        )
        AnimatedContent(
            targetState = state.listOfRequests,
            modifier = Modifier.fillMaxSize()
        ) {
            if (it.isNullOrEmpty()) {
                RequestsTabs(
                    state = state,
                    onTabSelected = { currentTab ->
                        listener.onTabClicked(currentTab)
                    }
                )
                NoServiceRequestPlaceholder(
                    modifier = Modifier.fillMaxSize()
                        .padding(horizontal = 40.dp)
                )
            } else {

                when (state.selectedTapIndex) {
                    Tabs.ONGOING -> {
                        Column(
                            modifier = Modifier.fillMaxSize()
                                .background(AppTheme.craftoColors.background.card)
                                .padding(16.dp)
                        ) {
                            CategoryItemHorizontal(
                                icon = painterResource(Res.drawable.star),
                                title = "category",
                                description = "category",
                                onClick = {},
                            )
                            Box(
                                Modifier.size(1.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                                    .background(AppTheme.craftoColors.shade.quaternary)
                            )
                            CraftsmanCard(
                                modifier = Modifier,
                                showOffers = true,
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

                    Tabs.COMPLETE -> {
                        Column(
                            modifier = Modifier.fillMaxSize()
                                .background(AppTheme.craftoColors.background.card)
                                .padding(16.dp)
                        ) {
                            CategoryItemHorizontal(
                                icon = painterResource(Res.drawable.star),
                                title = "category",
                                description = "category",
                                onClick = {},
                            )
                            Box(
                                Modifier.size(1.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                                    .background(AppTheme.craftoColors.shade.quaternary)
                            )
                            CraftsmanCard(
                                modifier = Modifier,
                                rating = 5.0,
                                numberOfOffers = 3,
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

                    Tabs.CANCEL -> {
                        Column(
                            modifier = Modifier.fillMaxSize()
                                .background(AppTheme.craftoColors.background.card)
                                .padding(16.dp)
                        ) {
                            CategoryItemHorizontal(
                                icon = painterResource(Res.drawable.star),
                                title = "category",
                                description = "category",
                                onClick = {},
                            )
                            Box(
                                Modifier.size(1.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(AppTheme.craftoRadius.full))
                                    .background(AppTheme.craftoColors.shade.quaternary)
                            )
                            CraftsmanCard(
                                modifier = Modifier,
                                rating = 5.0,
                                numberOfOffers = 3,
                                buttonText = "Rate",
                                buttonIcon = painterResource(Res.drawable.star),
                                buttonColors = ButtonDefaults.buttonColors(
                                    containerColor = AppTheme.craftoColors.shade.quinary,
                                    contentColor = AppTheme.craftoColors.button.onSecondary
                                ),
                                onButtonClick = {}
                            )
                        }
                    }
                }
            }

        }
//        RateBottomSheet(
//            updateRate = state.numberOfRating,
//            onDismiss = {
//                true
//                listener.onDismissBottomSheet()
//            },
//            onClick = {
//                listener.onRateClicked()
//            }
//        )
    }
}

@Composable
private fun AppBar(
    modifier: Modifier = Modifier
) {
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

@Composable
private fun RequestsTabs(
    state: CustomerRequestUiState,
    onTabSelected: (tab: Tabs) -> Unit,
    modifier: Modifier = Modifier

) {

    TabRow(
        selectedTabIndex = state.selectedTapIndex.ordinal,
        modifier = modifier.fillMaxWidth(),
        containerColor = AppTheme.craftoColors.background.card,
        indicator = {
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(it[state.selectedTapIndex.ordinal])
                    .height(2.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = AppTheme.craftoRadius.full,
                            topEnd = AppTheme.craftoRadius.full
                        )
                    )
                    .background(AppTheme.craftoColors.brand.primary)
            )
        },
        tabs = {
            Tabs.entries.forEach { tab ->
                Tab(
                    selected = state.selectedTapIndex == tab,
                    onClick = { onTabSelected(tab) },
                    selectedContentColor = AppTheme.craftoColors.brand.primary,
                    unselectedContentColor = AppTheme.craftoColors.shade.secondary,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    Text(
                        text = stringResource(tab.tabTitle),
                        modifier = Modifier.padding(16.dp),
                        style = AppTheme.textStyle.body.medium,
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun CustomerRequestContentPreview() {
    AppTheme(
        isDarkTheme = false,
    ) {
        CustomerRequestContent(
            listener = object : CustomerRequestInteractionListener {
                override fun onTabClicked(tabs: Tabs) {}
                override fun onRateClicked() {}
                override fun onDismissBottomSheet() {}
            },
            state = CustomerRequestUiState()
        )
    }
}