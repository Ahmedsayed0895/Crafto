package org.example.project.domain.entity

data class CustomerRequest(
    val id:  String,
    val title: String,
    val description: String,
    val status: CustomerIssueStatus,
    val category: Category,
    val customerId:  String,
    val governmentId:  String,
    val governmentName: String,
    val districtId:  String,
    val districtName: String,
    val locationDetails: String,
    val photos: List<String>,
    val offers: List<CraftsmanOffer>
){
    enum class CustomerIssueStatus {
        SUBMITTED,
        RECEIVING_OFFERS,
        CRAFTSMAN_SELECTED,
        IN_PROGRESS,
        DONE,
        CANCELED
    }
}
