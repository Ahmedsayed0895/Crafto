package org.example.project.data.model

import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Serializable
data class CraftsmanPortfolioLocalDto @OptIn(ExperimentalTime::class) constructor(
    val photoUrls: List<String> = emptyList(),
    val description: String = "",
    val localPhotoPaths: List<String> = emptyList(),
    val lastModified: Long = Clock.System.now().epochSeconds
)
