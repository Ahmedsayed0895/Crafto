package org.example.project.data.mapper

import org.example.project.data.model.CraftsmanIdentityLocalDto
import org.example.project.domain.entity.CraftsmanIdentity
import org.example.project.domain.entity.VerificationStatus

fun CraftsmanIdentity.toLocalDto() = CraftsmanIdentityLocalDto(
    frontIdUrl = frontIdUrl,
    backIdUrl = backIdUrl,
    verificationStatus = verificationStatus.name
)

fun CraftsmanIdentityLocalDto.toDomain() = CraftsmanIdentity(
    frontIdUrl = frontIdUrl,
    backIdUrl = backIdUrl,
    verificationStatus = try {
        VerificationStatus.valueOf(verificationStatus)
    } catch (e: Exception) {
        VerificationStatus.NOT_SUBMITTED
    }
)
