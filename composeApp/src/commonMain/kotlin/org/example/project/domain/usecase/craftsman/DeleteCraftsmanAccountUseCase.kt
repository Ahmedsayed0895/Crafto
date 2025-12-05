package org.example.project.domain.usecase.craftsman

import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository


class DeleteCraftsmanAccountUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(
        craftsmanId: String,
        confirmDelete: Boolean = false
    ): Boolean {
        if (craftsmanId.isBlank()) {
            throw ValidationException("Craftsman ID is required")
        }

        if (!confirmDelete) {
            throw ValidationException("Please confirm account deletion")
        }

        return repository.deleteCraftsmanAccount(craftsmanId)
    }
}