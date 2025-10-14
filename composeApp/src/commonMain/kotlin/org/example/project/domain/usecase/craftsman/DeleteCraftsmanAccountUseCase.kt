package org.example.project.domain.usecase.craftsman

import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository
import org.koin.core.annotation.Factory

@Factory
class DeleteCraftsmanAccountUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(
        craftsmanId: String,
        confirmDelete: Boolean = false
    ) {
        // Business validation
        if (craftsmanId.isBlank()) {
            throw ValidationException("Craftsman ID is required")
        }

        if (!confirmDelete) {
            throw ValidationException("Please confirm account deletion")
        }

        // Direct repository call
        repository.deleteCraftsmanAccount(craftsmanId)
    }
}