package org.example.project.presentation.ui.screens.setupScreens.component

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
import org.example.project.data.memory.dataSource.categoryList
import org.example.project.presentation.designsystem.components.Chip
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.ui.extension.toAnimatedColor
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupCategoryState
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CategoryActionBox(
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
                Chip(
                    text = category.title,
                    isSelected = isSelected,
                    onChipSelected = { onChipSelected(category.id) },
                    modifier = Modifier.background(
                        color = category.color.toAnimatedColor(
                            isSelected,
                            AppTheme.craftoColors.background.card
                        ),
                        shape = RoundedCornerShape(AppTheme.craftoRadius.full)
                    ),
                    textColor = AppTheme.craftoColors.background.card
                        .toAnimatedColor(
                            isSelected,
                            AppTheme.craftoColors.shade.secondary
                        ),
                    borderColor = category.color.toAnimatedColor(
                        condition = isSelected,
                        falseConditionColor = Color.Transparent,
                        duration = 100

                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun CategoryActionBoxLightPreview() {
    AppTheme {
        CategoryActionBox(
            state = AccountSetupState(
                categoryState = AccountSetupCategoryState(
                    categories = categoryList
                )
            ),
            onChipSelected = {}
        )

    }
}

@Preview
@Composable
fun CategoryActionBoxDarkPreview() {
    AppTheme(isDarkTheme = true) {
        CategoryActionBox(
            state = AccountSetupState(
                categoryState = AccountSetupCategoryState(
                    categories = categoryList
                )
            ),
            onChipSelected = {}
        )
    }
}