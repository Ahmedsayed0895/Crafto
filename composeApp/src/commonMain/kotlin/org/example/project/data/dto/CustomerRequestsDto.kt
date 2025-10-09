package org.example.project.data.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class CustomerRequestsDtoItem(

	@SerialName("issueContent")
	val issueContent: String? = null,

	@SerialName("districtName")
	val districtName: String? = null,

	@SerialName("issueTitle")
	val issueTitle: String? = null,

	@SerialName("customerId")
	val customerId: String? = null,

	@SerialName("locationDetails")
	val locationDetails: String? = null,

	@SerialName("governmentName")
	val governmentName: String? = null,

	@SerialName("category")
	val category: Category? = null,

	@SerialName("photos")
	val photos: List<String?>? = null
)

@Serializable
data class Category(

	@SerialName("categoryColor")
	val categoryColor: String? = null,

	@SerialName("id")
	val id: String? = null,

	@SerialName("categoryName")
	val categoryName: String? = null
)
