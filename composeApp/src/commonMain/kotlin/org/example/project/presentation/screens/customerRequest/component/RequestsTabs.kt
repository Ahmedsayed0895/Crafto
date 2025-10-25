package org.example.project.presentation.screens.customerRequest.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.customerRequest.CustomerRequestScreenUiState
import org.jetbrains.compose.resources.stringResource

@Composable
fun RequestsTabs(
    state: CustomerRequestScreenUiState,
    onTabSelected: (CustomerRequestScreenUiState.RequestsTab) -> Unit,
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
        }
    ) {
        CustomerRequestScreenUiState.RequestsTab.entries.forEach { tab ->
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
}
