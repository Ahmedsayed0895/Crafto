package org.example.project.data.repository.dataSource

import org.example.project.data.dto.Category

interface CategoryDataSource {
    suspend fun getCategories(): List<Category>
}