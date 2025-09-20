package org.example.project.domain.entity

data class CraftsmanIdentity(
    val frontIdUrl: String? = null,
    val backIdUrl: String? = null,
    val verificationStatus: VerificationStatus = VerificationStatus.NOT_SUBMITTED
)
