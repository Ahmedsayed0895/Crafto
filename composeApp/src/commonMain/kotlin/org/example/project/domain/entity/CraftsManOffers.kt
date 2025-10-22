package org.example.project.domain.entity

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class CraftsmanOffer(
    val id: Uuid,
    val craftsmanId: Uuid,
    val customerId: Uuid,
    val customerIssueId: Uuid,
    val price: Double,
    val createdDate: String,
    val visitedDate: String?,
    val message: String,
    val isSelected: Boolean
)