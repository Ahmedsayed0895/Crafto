package org.example.project.domain.usecase

import org.example.project.domain.entity.Category
import org.example.project.domain.repository.CategoryRepository

class GetCategoriesUseCase(val repository: CategoryRepository) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories().filter { it.isCustomer }
    }
}