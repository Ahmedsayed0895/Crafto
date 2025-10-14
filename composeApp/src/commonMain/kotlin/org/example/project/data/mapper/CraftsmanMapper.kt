package org.example.project.data.mapper

import org.example.project.data.dto.CraftsmanProfileResponseDto
import org.example.project.data.dto.PersonalInfoDto
import org.example.project.data.dto.VerificationInfoDto
import org.example.project.domain.entity.Craftsman
import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.entity.PersonalInfo
import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.entity.VerificationStatus
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun CraftsmanProfileResponseDto.toDomain(): Craftsman {
    return Craftsman(
        craftsmanId = craftsmanId,
        personalInfo = personalInfo.toDomain(),
        categories = categories,
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

fun PersonalInfoDto.toDomain(): PersonalInfo {
    return PersonalInfo(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        address = address
    )
}

fun PersonalInfo.toDto(): PersonalInfoDto {
    return PersonalInfoDto(
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

