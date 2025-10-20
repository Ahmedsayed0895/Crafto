package org.example.project.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OnboardingDto(
    @SerialName("id")
    val id : String,
    @SerialName("title")
    val title : String,
    @SerialName("imageRes")
    val imageUrl : String,
    @SerialName("description")
    val description : String
)