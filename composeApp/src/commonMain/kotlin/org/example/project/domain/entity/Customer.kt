package org.example.project.domain.entity

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class CustomerProfile @OptIn(ExperimentalTime::class) constructor(
    val customerId: String,
    val personalInfo: CustomerPersonalInfo,
    val categories: List<String>,
    val location: CustomerLocation,
    val profilePictureUrl: String? = null,
    val createdAt: Instant
)

data class CustomerPersonalInfo(
    val name: String,
    val phoneNumber: String,
)

data class CustomerLocation(
    val governorate: String,
    val district: String,
    val detailedLocation: String
)