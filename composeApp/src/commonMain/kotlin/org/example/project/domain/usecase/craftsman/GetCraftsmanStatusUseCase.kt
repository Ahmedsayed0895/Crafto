package org.example.project.domain.usecase.craftsman

import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository
import org.koin.core.annotation.Factory

@Factory
class GetCraftsmanStatusUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(craftsmanId: String): CraftsmanStatus {
        // Business validation
        if (craftsmanId.isBlank()) {
            throw ValidationException("Craftsman ID is required")
        }

        // Direct repository call
        return repository.getCraftsmanStatus(craftsmanId)
    }
}