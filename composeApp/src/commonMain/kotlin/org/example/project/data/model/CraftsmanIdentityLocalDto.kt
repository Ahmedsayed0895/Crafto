package org.example.project.data.model

import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Serializable
data class CraftsmanIdentityLocalDto @OptIn(ExperimentalTime::class) constructor(
    val frontIdUrl: String? = null,
    val backIdUrl: String? = null,
    val verificationStatus: String = "NOT_SUBMITTED",
    val lastModified: Long = Clock.System.now().epochSeconds
)
