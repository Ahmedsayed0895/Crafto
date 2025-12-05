package org.example.project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class DeleteAccountResponseDto(
    val success: Boolean,
    val message: String
)
