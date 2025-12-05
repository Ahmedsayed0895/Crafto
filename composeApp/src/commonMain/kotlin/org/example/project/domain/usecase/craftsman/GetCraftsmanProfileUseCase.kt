package org.example.project.domain.usecase.craftsman

import org.example.project.domain.entity.CraftsmanProfile
import org.example.project.domain.repository.CraftsmanRepository


class GetCraftsmanProfileUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(): CraftsmanProfile {
        return repository.getCraftsmanProfile()
    }
}