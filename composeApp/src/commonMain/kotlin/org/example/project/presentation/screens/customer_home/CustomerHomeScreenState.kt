package org.example.project.presentation.screens.customer_home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class CustomerHomeScreenState(
    val isError: Boolean? = null,
    val mostRequestedCategories: List<RequestCategory> = listOf(),
    val allCategories: List<RequestCategory> = listOf()
){
    data class RequestCategory(
        val title: String,
        val content: String,
        val icon: Painter,
        val iconBackgroundColor: Color,
        val iconTint: Color,
    )
}
