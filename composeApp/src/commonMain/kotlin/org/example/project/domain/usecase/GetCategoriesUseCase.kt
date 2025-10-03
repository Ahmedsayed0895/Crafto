package org.example.project.domain.usecase

import org.example.project.domain.entity.Category
import org.example.project.domain.repository.CategoryRepository
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Single
class GetCategoriesUseCase(
    @Provided val repository: CategoryRepository) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}