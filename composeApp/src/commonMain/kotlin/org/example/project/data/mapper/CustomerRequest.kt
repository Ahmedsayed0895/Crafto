package org.example.project.data.mapper

import org.example.project.data.dto.Category
import org.example.project.data.dto.CustomerRequestDto
import org.example.project.data.dto.OffersItem
import org.example.project.domain.entity.CraftsmanOffer
import org.example.project.domain.entity.Category as DomainCategory
import org.example.project.domain.entity.CustomerRequest as DomainCustomerRequest

fun CustomerRequestDto.toDomain(): DomainCustomerRequest {
    return DomainCustomerRequest(
        id = id?:"",
        title = title?:"",
        description = description?:"",
        status = DomainCustomerRequest.CustomerIssueStatus.valueOf(status?:""),
        category = category?.toDomain() ?: DomainCategory(
            id = "",
            title = "",
            color = "",
            iconUrl = ""
        ),
        customerId = customerId?:"",
        governmentId = governmentId?:"",
        governmentName = governmentName ?: "",
        districtId = districtId?:"",
        districtName = districtName ?: "",
        locationDetails = locationDetails?:"",
        photos = photos?:emptyList(),
        offers = offers?.map { it.toDomain() } ?: emptyList()
    )
}

fun Category.toDomain(): DomainCategory {
    return DomainCategory(
        id = categoryId ?: "",
        title = categoryName ?: "",
        color = categoryColor ?: "",
        iconUrl = categoryIconUrl ?: ""
    )
}

fun OffersItem.toDomain(): CraftsmanOffer {
    return CraftsmanOffer(
        id = id ?: "",
        craftsmanId = craftsmanId ?: "",
        customerId = customerId ?: "",
        customerIssueId = customerIssueId ?: "",
        price = price ?: 0.0,
        createdDate = createdDate ?: "",
        visitedDate = visitedDate ?: "",
        message = message ?: "",
        isSelected = isSelected ?: false
    )
}
