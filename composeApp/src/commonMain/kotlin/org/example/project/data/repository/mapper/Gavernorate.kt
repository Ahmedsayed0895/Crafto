package org.example.project.data.repository.mapper

import org.example.project.data.dto.GovernoratesDto
import org.example.project.domain.entity.Governorates

fun List<GovernoratesDto>.toGovernorates(): List<Governorates> {
    return map { dto ->
        Governorates(
            id = dto.id,
            name = dto.name
        )
    }
}