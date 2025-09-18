package org.example.project.data.repository.dataSource

import org.example.project.data.repository.dataSource.local.dto.CategoryEntity

interface CategoryDataSource {
    suspend fun getCategories(): List<CategoryEntity>
}