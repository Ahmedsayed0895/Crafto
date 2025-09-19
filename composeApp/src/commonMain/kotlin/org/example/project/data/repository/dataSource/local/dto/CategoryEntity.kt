package org.example.project.data.repository.dataSource.local.dto

import androidx.compose.ui.graphics.Color

data class CategoryEntity(
    val id: Int,
    val title: String,
    val isSelected: Boolean,
    val color: Color,
)
