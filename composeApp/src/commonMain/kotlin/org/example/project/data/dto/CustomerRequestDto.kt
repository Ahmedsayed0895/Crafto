package org.example.project.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class CustomerRequestDto(

    @SerialName("offers")
    val offers: List<OffersItem>? = null,

    @SerialName("districtName")
    val districtName: String,

    @SerialName("description")
    val description: String,

    @SerialName("governmentName")
    val governmentName: String,

    @SerialName("title")
    val title: String,

    @SerialName("photos")
    val photos: List<String?>,

    @SerialName("districtId")
    val districtId: Uuid,

    @SerialName("customerId")
    val customerId: Uuid,

    @SerialName("locationDetails")
    val locationDetails: String,

    @SerialName("id")
    val id: Uuid,

    @SerialName("category")
    val category: Category,

    @SerialName("status")
    val status: String,

    @SerialName("governmentId")
    val governmentId: Uuid
)

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class Category(

    @SerialName("categoryColor")
    val categoryColor: String,

    @SerialName("categoryName")
    val categoryName: String,

    @SerialName("categoryId")
    val categoryId: Uuid
)

@OptIn(ExperimentalUuidApi::class)
@Serializable
data class OffersItem(

    @SerialName("customerIssueId")
    val customerIssueId: Uuid,

    @SerialName("createdDate")
    val createdDate: String,

    @SerialName("price")
    val price: Double,

    @SerialName("customerId")
    val customerId: Uuid,

    @SerialName("visitedDate")
    val visitedDate: String,

    @SerialName("isSelected")
    val isSelected: Boolean,

    @SerialName("id")
    val id: Uuid,

    @SerialName("craftsmanId")
    val craftsmanId: Uuid,

    @SerialName("message")
    val message: String
)
