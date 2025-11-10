package org.example.project.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GovernoratesDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String
)