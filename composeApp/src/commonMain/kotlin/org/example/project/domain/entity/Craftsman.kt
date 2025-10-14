package org.example.project.domain.entity

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Craftsman @OptIn(ExperimentalTime::class) constructor(
    val craftsmanId: String,
    val personalInfo: PersonalInfo,
    val categories: List<String>,
    val status: CraftsmanStatus,
    val verificationStatus: VerificationStatus,
    val verification: VerificationDocuments,
    val createdAt: Instant,
)

data class PersonalInfo(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val address: String
)

data class VerificationDocuments(
    val idCardFrontUrl: String? = null,
    val idCardBackUrl: String? = null,
    val workPortfolioUrls: List<String> = emptyList()
)

enum class CraftsmanStatus {
    PENDING_VERIFICATION,
    ACTIVE,
    SUSPENDED,
    REJECTED
}

enum class VerificationStatus {
    NOT_SUBMITTED,
    PENDING,
    VERIFIED,
    REJECTED
}
