package org.example.project.data.remote.mapper

import org.example.project.data.remote.dto.DistrictDto
import org.example.project.domain.entity.District

fun List<DistrictDto>.toDistrictEntityList(): List<District> {
    return map { dto ->
        District(
            id = dto.id,
            name = dto.name
        )
    }
}