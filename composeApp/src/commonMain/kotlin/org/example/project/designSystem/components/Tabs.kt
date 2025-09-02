package org.example.project.designSystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Tabs(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
) {
    val selectedContentColor = AppTheme.craftoColors.brand.primary
    val unselectedContentColor = AppTheme.craftoColors.shade.secondary

    TabRow(
        selectedTabIndex = selectedTab,
        contentColor = selectedContentColor,
        containerColor = AppTheme.craftoColors.background.card,
        modifier = modifier,
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab])
                    .padding(horizontal = 16.dp),
                color = selectedContentColor,
            )
        },
        divider = {
            HorizontalDivider(
                thickness = 1.dp,
                color = AppTheme.craftoColors.stroke.primary
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            val isSelected = selectedTab == index
            Tab(
                selected = isSelected,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                text = {
                    Text(
                        text = title,
                        style = AppTheme.textStyle.body.medium
                    )
                },
                onClick = { onTabSelected(index) }
            )
        }
    }
}

@Preview
@Composable
private fun TabsPreview(){
    var selectedTab by remember { mutableIntStateOf(0) }

    Tabs(
        tabs = listOf("First", "Second", "Third"),
        selectedTab = selectedTab,
        onTabSelected = { newTab -> selectedTab = newTab },
    )
}