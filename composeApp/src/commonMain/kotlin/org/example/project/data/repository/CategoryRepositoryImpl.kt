package org.example.project.data.repository


import org.example.project.data.remote.datasource.CategoryDataSource
import org.example.project.data.mapper.toDomain
import org.example.project.domain.entity.Category
import org.example.project.domain.repository.CategoryRepository


class CategoryRepositoryImpl(
    private val dataSource: CategoryDataSource
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> =
        dataSource.getCategories().map { it.toDomain() }
}