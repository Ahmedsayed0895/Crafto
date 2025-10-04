package org.example.project.presentation.screens.customerRequest

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import crafto.composeapp.generated.resources.notifications
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.viewmodel.customerRequest.CustomerRequestUiState
import org.example.project.presentation.viewmodel.customerRequest.CustomerRequestViewModel
import org.example.project.presentation.viewmodel.customerRequest.Tabs
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CustomerRequestScreen(
    viewModel: CustomerRequestViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    CustomerRequestContent(
//        listener = viewModel,
        state = state
    )
}


@Composable
private fun CustomerRequestContent(
//    listener: CustomerRequestInteractionListener,
    state: CustomerRequestUiState,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .background(AppTheme.craftoColors.background.card)
                .statusBarsPadding()
                .padding(vertical = 18.dp, horizontal = 16.dp),
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

//        NoServiceRequestPlaceholder(
//            modifier = modifier.fillMaxSize()
//                .padding(horizontal = 40.dp)
//        )
        RequestsTabs(
            state = state,
            onTabSelected = { currentTab ->
                when(currentTab){
                    Tabs.ONGOING -> {}
                    Tabs.COMPLETE -> {}
                    Tabs.CANCEL -> {}
                }
            }
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
                        text = tab.name,
                        modifier = Modifier.padding(16.dp),
                        style = AppTheme.textStyle.body.medium
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
//            listener = object : CustomerRequestInteractionListener {},
            state = CustomerRequestUiState()
        )
    }
}