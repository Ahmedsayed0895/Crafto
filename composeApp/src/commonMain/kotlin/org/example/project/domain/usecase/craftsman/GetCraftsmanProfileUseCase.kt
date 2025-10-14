package org.example.project.domain.usecase.craftsman

import org.example.project.domain.entity.Craftsman
import org.example.project.domain.repository.CraftsmanRepository
import org.koin.core.annotation.Factory

@Factory
class GetCraftsmanProfileUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(): Craftsman {
        return repository.getCraftsmanProfile()
    }
}