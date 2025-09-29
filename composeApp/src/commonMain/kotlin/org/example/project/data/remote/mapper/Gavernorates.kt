package org.example.project.data.remote.mapper

import org.example.project.data.remote.dto.DistrictDto
import org.example.project.data.remote.dto.GovernoratesDto
import org.example.project.domain.entity.District
import org.example.project.domain.entity.Governorates

fun List<GovernoratesDto>.toGovernoratesEntityList(): List<Governorates> {
    return map { dto ->
        Governorates(
            id = dto.id,
            name = dto.name
        )
    }
}

