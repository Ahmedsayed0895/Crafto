package org.example.project.data.repository.mapper

import org.example.project.data.repository.dataSource.memory.dto.CategoryEntity
import org.example.project.domain.entity.Category

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = id,
        title = title,
        isSelected = isSelected,
        color = color,
    )
}

fun CategoryEntity.toCategoryDomain(): Category {
    return Category(
        id = id,
        title = title,
        isSelected = isSelected,
        color = color,
    )
}