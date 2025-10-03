package org.example.project.data.memory.dataSource

import org.example.project.data.repository.dataSource.CategoryDataSource
import org.example.project.data.repository.dataSource.memory.dto.CategoryEntity
import org.example.project.data.repository.mapper.toCategoryEntity
import org.example.project.domain.entity.Category
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Single(binds = [CategoryDataSource::class])
class CategoryDataSourceImpl(
    @Provided val categoryList: List<Category>
) : CategoryDataSource {
    override suspend fun getCategories(): List<CategoryEntity> {
        return categoryList.map { it.toCategoryEntity() }
    }
}