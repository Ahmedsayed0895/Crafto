package org.example.project.domain.entity

import androidx.compose.ui.graphics.Color

data class Category(
    val id: Int,
    val title: String,
    val isSelected: Boolean,
    val color: Color,
)
