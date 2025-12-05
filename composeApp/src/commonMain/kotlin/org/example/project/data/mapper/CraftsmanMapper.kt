package org.example.project.data.mapper

import org.example.project.data.remote.dto.CategoryDto
import org.example.project.data.remote.dto.CraftsmanProfileResponseDto
import org.example.project.data.remote.dto.CraftsmanPersonalInfoDto
import org.example.project.data.remote.dto.VerificationInfoDto
import org.example.project.domain.entity.Category
import org.example.project.domain.entity.CraftsmanProfile
import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.entity.CraftsmanPersonalInfo
import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.entity.VerificationStatus
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun CraftsmanProfileResponseDto.toDomain(): CraftsmanProfile {
    return CraftsmanProfile(
        craftsmanId = craftsmanId,
        craftsmanPersonalInfo = personalInfo.toDomain(),
        categories = categories,
        profilePictureUrl = profilePictureUrl,
        status = CraftsmanStatus.valueOf(status),
        verificationStatus = VerificationStatus.valueOf(verificationInfo.status),
        verification = verificationInfo.toVerificationDocuments(),
        createdAt = Instant.parse(createdAt)
    )
}

fun VerificationInfoDto.toVerificationDocuments(): VerificationDocuments {
    return VerificationDocuments(
        idCardFrontUrl = idCardFrontUrl,
        idCardBackUrl = idCardBackUrl,
        workPortfolioUrls = workPortfolioUrls
    )
}

fun CraftsmanPersonalInfoDto.toDomain(): CraftsmanPersonalInfo {
    return CraftsmanPersonalInfo(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        address = address
    )
}

fun CraftsmanPersonalInfo.toDto(): CraftsmanPersonalInfoDto {
    return CraftsmanPersonalInfoDto(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        address = address
    )
}

fun String.toCraftsmanStatus(): CraftsmanStatus {
    return try {
        CraftsmanStatus.valueOf(this)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Invalid craftsman status: $this")
    }
}

fun String.toVerificationStatus(): VerificationStatus {
    return try {
        VerificationStatus.valueOf(this)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Invalid verification status: $this")
    }
}

fun CategoryDto.toDomain(): Category = Category(
    id = id,
    title = title,
    colorHex = colorHex
)