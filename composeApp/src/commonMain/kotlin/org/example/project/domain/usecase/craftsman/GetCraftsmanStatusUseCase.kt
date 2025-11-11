package org.example.project.domain.usecase.craftsman

import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository


class GetCraftsmanStatusUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(craftsmanId: String): CraftsmanStatus {
        if (craftsmanId.isBlank()) {
            throw ValidationException("Craftsman ID is required")
        }

        return repository.getCraftsmanStatus(craftsmanId)
    }
}