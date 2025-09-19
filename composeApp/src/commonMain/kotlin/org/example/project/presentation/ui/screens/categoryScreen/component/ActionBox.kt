package org.example.project.presentation.ui.screens.categoryScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.domain.entity.Category
import org.example.project.presentation.designsystem.components.Chip
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupState

@Composable
fun ActionBox(
    modifier: Modifier = Modifier,
    state: AccountSetupState,
    onChipSelected: (id: Int) -> Unit,
) {
    Box(modifier = modifier.fillMaxWidth()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            state.categoryState.categories.forEach { category ->
                val isSelected = category.isSelected
                val (chipColor, textColor) =
                    chipColorsChanger(isSelected = isSelected, category = category).first to
                            chipColorsChanger(isSelected = isSelected, category = category).second
                Chip(
                    text = category.title,
                    isSelected = category.isSelected,
                    onChipSelected = { onChipSelected(category.id) },
                    modifier = Modifier.background(
                        color = chipColor,
                        shape = RoundedCornerShape(AppTheme.craftoRadius.full)
                    ),
                    textColor = textColor
                )
            }
        }
    }
}

@Composable
private fun chipColorsChanger(isSelected: Boolean, category: Category): Pair<Color, Color> {
    return if (isSelected) {
        category.color to AppTheme.craftoColors.background.card
    } else {
        AppTheme.craftoColors.background.card to AppTheme.craftoColors.shade.secondary


    }
}