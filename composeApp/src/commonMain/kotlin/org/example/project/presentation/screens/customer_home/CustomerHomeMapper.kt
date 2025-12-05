package org.example.project.presentation.screens.customer_home

import androidx.compose.ui.graphics.Color
import org.example.project.domain.entity.Category

fun Category.toUi() = CustomerHomeScreenState.RequestCategoryUiState(
    title = title,
    content = "Pipes, faucets, water heaters",
    icon = null,
    iconBackgroundColor = Color(colorHex).copy(alpha = 10f),
    iconTint = Color(colorHex)
)