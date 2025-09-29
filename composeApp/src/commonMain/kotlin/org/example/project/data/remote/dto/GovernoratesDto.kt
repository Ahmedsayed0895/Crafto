package org.example.project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GovernoratesResponse(
    @SerialName("data") val governoratesDto: List<GovernoratesDto>? = null
)

@Serializable
data class GovernoratesDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String
)

