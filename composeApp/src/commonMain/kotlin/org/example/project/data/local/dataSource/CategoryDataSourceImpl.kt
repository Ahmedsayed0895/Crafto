package org.example.project.data.local.dataSource

import androidx.compose.ui.graphics.Color
import org.example.project.data.repository.dataSource.CategoryDataSource
import org.example.project.data.repository.dataSource.local.dto.CategoryEntity
import org.example.project.data.repository.mapper.toCategoryEntity
import org.example.project.domain.entity.Category

class CategoryDataSourceImpl : CategoryDataSource {
    override suspend fun getCategories(): List<CategoryEntity> {
        return categoryList.map { it.toCategoryEntity() }
    }
}

private val categoryList = listOf(
    Category(
        id = 1,
        title = "Plumbing",
        isSelected = false,
        color = Color(0xFF9B59B6),
    ),         // Amethyst Purple
    Category(
        id = 2,
        title = "Electrical",
        isSelected = false,
        color = Color(0xFF1ABC9C),
    ),       // Turquoise
    Category(
        id = 3,
        title = "Cleaning",
        isSelected = false,
        color = Color(0xFF3498DB),
    ),         // Peter River Blue
    Category(
        id = 4,
        title = "AC Repair",
        isSelected = false,
        color = Color(0xFFF39C12),
    ),        // Orange
    Category(
        id = 5,
        title = "Furniture",
        isSelected = false,
        color = Color(0xFFD35400),
    ),        // Pumpkin Orange
    Category(
        id = 6,
        title = "Painting",
        isSelected = false,
        color = Color(0xFF34495E),
    ),         // Wet Asphalt
    Category(
        id = 7,
        title = "Carpentry",
        isSelected = false,
        color = Color(0xFFE67E22),
    ),        // Carrot Orange
    Category(
        id = 8,
        title = "Roofing",
        isSelected = false,
        color = Color(0xFF7F8C8D),
    ),          // Slate Gray
    Category(
        id = 9,
        title = "Landscaping",
        isSelected = false,
        color = Color(0xFF2ECC71),
    ),      // Emerald Green
    Category(
        id = 10,
        title = "Pest Control",
        isSelected = false,
        color = Color(0xFFC0392B),
    ),     // Pomegranate Red
    Category(
        id = 11,
        title = "Appliance Repair",
        isSelected = false,
        color = Color(0xFF00BCD4),
    ), // Cyan
    Category(
        id = 12,
        title = "Pool Maintenance",
        isSelected = false,
        color = Color(0xFF8E44AD),
    ), // Wisteria Purple
    Category(
        id = 13,
        title = "HVAC Maintenance",
        isSelected = false,
        color = Color(0xFF27AE60),
    ), // Nephritis Green
)