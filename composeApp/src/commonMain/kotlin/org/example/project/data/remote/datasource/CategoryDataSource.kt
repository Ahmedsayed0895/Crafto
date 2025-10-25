package org.example.project.data.remote.datasource

import org.example.project.data.remote.dto.CategoryDto

interface CategoryDataSource {
    suspend fun getCategories(): List<CategoryDto>
}