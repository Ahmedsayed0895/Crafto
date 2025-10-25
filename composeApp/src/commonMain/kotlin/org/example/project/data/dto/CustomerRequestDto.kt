package org.example.project.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomerRequestDto(

    @SerialName("offers")
    val offers: List<OffersItem>? = null,

    @SerialName("districtName")
    val districtName: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("governmentName")
    val governmentName: String? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("photos")
    val photos: List<String>? = null,

    @SerialName("districtId")
    val districtId: String? = null,

    @SerialName("customerId")
    val customerId: String? = null,

    @SerialName("locationDetails")
    val locationDetails: String? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("category")
    val category: Category? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("governmentId")
    val governmentId: String? = null
)

@Serializable
data class Category(

    @SerialName("categoryColor")
    val categoryColor: String? = null,

    @SerialName("categoryIconUrl")
    val categoryIconUrl: String? = null,

    @SerialName("categoryName")
    val categoryName: String? = null,

    @SerialName("categoryId")
    val categoryId: String? = null,

    @SerialName("categoryDescription")
    val categoryDescription: String? = null
)

@Serializable
data class OffersItem(

    @SerialName("customerIssueId")
    val customerIssueId: String? = null,

    @SerialName("createdDate")
    val createdDate: String? = null,

    @SerialName("price")
    val price: Double? = null,

    @SerialName("customerId")
    val customerId: String? = null,

    @SerialName("visitedDate")
    val visitedDate: String? = null,

    @SerialName("isSelected")
    val isSelected: Boolean? = null,

    @SerialName("id")
    val id: String? = null,

    @SerialName("craftsmanId")
    val craftsmanId: String? = null,

    @SerialName("message")
    val message: String? = null
)
