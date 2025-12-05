package org.example.project.presentation.screens.customer_home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import org.example.project.presentation.shared.base.ErrorUiState

data class CustomerHomeScreenState(
    val isError: ErrorUiState? = null,
    val isLoading: Boolean = false,
    val customer: CustomerUiState = CustomerUiState(),
    val mostRequestedCategories: List<RequestCategoryUiState> = listOf(),
    val allCategories: List<RequestCategoryUiState> = listOf()
){
    data class RequestCategoryUiState(
        val title: String,
        val content: String,
        val icon: Painter?,
        val iconBackgroundColor: Color,
        val iconTint: Color,
    )
    data class CustomerUiState(
        val name: String = "",
        val location: String = "",
    )
}
