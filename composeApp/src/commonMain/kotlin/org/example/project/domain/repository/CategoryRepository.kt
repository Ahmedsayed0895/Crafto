package org.example.project.domain.repository

import org.example.project.domain.entity.Category


interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}