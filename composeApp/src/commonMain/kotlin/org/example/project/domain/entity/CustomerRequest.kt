package org.example.project.domain.entity

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class CustomerRequest(
    val id: Uuid,
    val title: String,
    val description: String,
    val status: CustomerIssueStatus,
    val category: Category,
    val customerId: Uuid,
    val governmentId: Uuid,
    val governmentName: String,
    val districtId: Uuid,
    val districtName: String,
    val locationDetails: String,
    val photos: List<String>,
    val offers: List<CraftsmanOffer>
){
    enum class CustomerIssueStatus {
        RECEIVING_OFFERS,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }
}
