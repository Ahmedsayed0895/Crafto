package org.example.project.domain.repository

import org.example.project.domain.entity.District
import org.example.project.domain.entity.Governorates

interface LocationRepository {
    suspend fun getAllGovernorates(): List<Governorates>
    suspend fun getDistrictsByGovernorateId(governorateId: String): List<District>
}