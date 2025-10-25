package org.example.project.domain.entity


data class CraftsmanOffer(
    val id: String,
    val craftsmanId: String,
    val customerId: String,
    val customerIssueId: String,
    val price: Double,
    val createdDate: String,
    val visitedDate: String?,
    val message: String,
    val isSelected: Boolean
)