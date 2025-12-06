package org.example.project.domain.usecase.location

import org.example.project.domain.entity.District
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.LocationRepository

class GetDistrictsByGovernorateUseCase(
    private val repository: LocationRepository
) {
    suspend operator fun invoke(governorateId: String): List<District> {
        if (governorateId.isBlank()) {
            throw ValidationException("Governorate ID is required")
        }
        return repository.getDistrictsByGovernorateId(governorateId)
    }
}
