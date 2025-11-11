package org.example.project.data.local.datasource

import org.example.project.data.remote.datasource.CategoryDataSource
import org.example.project.data.remote.dto.CategoryDto

class CategoryMemoryDataSource(
    private val seed: List<CategoryDto>
) : CategoryDataSource {
    override suspend fun getCategories(): List<CategoryDto> = seed
}