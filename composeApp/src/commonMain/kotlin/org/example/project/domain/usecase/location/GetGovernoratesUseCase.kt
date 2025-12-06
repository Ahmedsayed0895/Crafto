package org.example.project.domain.usecase.location

import org.example.project.domain.entity.Governorates
import org.example.project.domain.repository.LocationRepository

class GetGovernoratesUseCase(
    private val repository: LocationRepository
) {
    suspend operator fun invoke(): List<Governorates> {
        return repository.getAllGovernorates()
    }
}