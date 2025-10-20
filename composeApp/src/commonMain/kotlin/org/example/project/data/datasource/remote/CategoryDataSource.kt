package org.example.project.data.datasource.remote

import org.example.project.data.remote.dto.CategoryDto

interface CategoryDataSource {
    suspend fun getCategories(): List<CategoryDto>
}