package org.example.project.data.repository

import org.example.project.data.repository.dataSource.CategoryDataSource
import org.example.project.data.repository.mapper.toCategoryDomain
import org.example.project.domain.entity.Category
import org.example.project.domain.repository.CategoryRepository
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Single(binds = [CategoryRepository::class])
class CategoryRepositoryImpl(
   @Provided val dataSource: CategoryDataSource
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        return dataSource.getCategories().map { it.toCategoryDomain() }
    }
}