package org.example.project.data.repository.mapper

import org.example.project.data.dto.DistrictDto
import org.example.project.domain.entity.District

fun List<DistrictDto>.toDistrict(): List<District> {
    return map { dto ->
        District(
            id = dto.id,
            name = dto.name,
            governorateId = dto.governorateId
        )
    }
}