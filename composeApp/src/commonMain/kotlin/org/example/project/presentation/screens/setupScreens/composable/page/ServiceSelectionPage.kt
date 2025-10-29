package org.example.project.presentation.screens.setupscreens.composable.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.presentation.designsystem.components.Chip
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.model.CategoryUi

@Composable
fun ServiceSelectionPage(
    availableCategories: List<CategoryUi>,
    selectedServiceIds: Set<Int>,
    onCategoryToggled: (Int) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        availableCategories.forEach { category ->
            Chip(
                isSelected = category.id in selectedServiceIds,
                onChipSelected = { onCategoryToggled(category.id) },
                textColor= if (category.id in selectedServiceIds)
                    AppTheme.craftoColors.background.card
                else
                    AppTheme.craftoColors.shade.secondary,
                text = category.title,
                selectedBackgroundColor = category.color,
                unselectedBackgroundColor = AppTheme.craftoColors.background.card,
            )
        }
    }
}