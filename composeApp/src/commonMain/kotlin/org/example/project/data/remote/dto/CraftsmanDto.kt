package org.example.project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateCraftsmanRequest(
    val personalInfo: CraftsmanPersonalInfoDto,
    val categories: List<String>
)

@Serializable
data class CraftsmanSetupResponseDto(
    val craftsmanId: String,
    val status: String,
    val profilePictureUrl: String? = null,
    val message: String
)

@Serializable
data class IdCardUploadResponseDto(
    val craftsmanId: String,
    val idCardFrontUrl: String,
    val idCardBackUrl: String,
    val message: String,
    val idVerificationStatus: String
)

@Serializable
data class WorkPortfolioResponseDto(
    val craftsmanId: String,
    val workImageUrls: List<String>,
    val message: String,
    val totalImages: Int
)

@Serializable
data class CraftsmanProfileResponseDto(
    val craftsmanId: String,
    val personalInfo: CraftsmanPersonalInfoDto,
    val categories: List<String>,
    val profilePictureUrl: String? = null,
    val status: String,
    val verificationInfo: VerificationInfoDto,
    val createdAt: String
)

@Serializable
data class CraftsmanStatusResponseDto(
    val craftsmanId: String,
    val status: String,
    val profilePictureUrl: String? = null,
    val verificationStatus: String,
    val message: String
)

@Serializable
data class CraftsmanPersonalInfoDto(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val address: String
)

@Serializable
data class VerificationInfoDto(
    val status: String,
    val idCardFrontUrl: String?,
    val idCardBackUrl: String?,
    val workPortfolioUrls: List<String>
)