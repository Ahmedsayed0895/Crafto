package org.example.project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateCraftsmanRequest(
    val personalInfo: PersonalInfoDto,
    val categories: List<String>
)

@Serializable
data class CraftsmanSetupResponseDto(
    val craftsmanId: String,
    val status: String,
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
    val personalInfo: PersonalInfoDto,
    val categories: List<String>,
    val status: String,
    val verificationInfo: VerificationInfoDto,
    val createdAt: String
)

@Serializable
data class CraftsmanStatusResponseDto(
    val craftsmanId: String,
    val status: String,
    val verificationStatus: String,
    val message: String
)

@Serializable
data class DeleteAccountResponseDto(
    val success: Boolean,
    val message: String
)

@Serializable
data class PersonalInfoDto(
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

@Serializable
data class ErrorResponseDto(
    val code: String,
    val message: String,
    val timestamp: String
)


data class CategoryDto(
    val id: Int,
    val title: String,
    val colorHex: Long
)