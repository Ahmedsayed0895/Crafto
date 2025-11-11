package org.example.project.presentation.model

import androidx.compose.ui.graphics.Color

data class CategoryUi(
    val id: Int,
    val title: String,
    val color: Color,
    val isSelected: Boolean = false
)
