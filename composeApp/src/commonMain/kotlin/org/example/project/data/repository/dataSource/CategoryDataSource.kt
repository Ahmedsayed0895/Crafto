package org.example.project.data.repository.dataSource

import org.example.project.data.repository.dataSource.memory.dto.CategoryEntity

interface CategoryDataSource {
    suspend fun getCategories(): List<CategoryEntity>
}