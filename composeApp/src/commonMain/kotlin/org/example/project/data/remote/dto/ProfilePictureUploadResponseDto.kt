package org.example.project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProfilePictureUploadResponseDto(
    val userId: String,
    val profilePictureUrl: String,
    val message: String
)
