package org.example.project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DistrictResponse(
    @SerialName("data") val districtDto: List<DistrictDto>? = null
)

@Serializable
data class DistrictDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String
)