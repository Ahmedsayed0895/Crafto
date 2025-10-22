package org.example.project.data.mapper

import org.example.project.data.dto.Category
import org.example.project.data.dto.CustomerRequestDto
import org.example.project.data.dto.OffersItem
import org.example.project.domain.entity.CraftsmanOffer
import org.example.project.domain.entity.CustomerRequest
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)

fun CustomerRequestDto.toDomain(): CustomerRequest {
    return CustomerRequest(
        id = id,
        title = title,
        description = description,
        status = CustomerRequest.CustomerIssueStatus.valueOf(status),
        category = category.toDomain(),
        customerId = customerId,
        governmentId = governmentId,
        governmentName = governmentName,
        districtId = districtId,
        districtName = districtName,
        locationDetails = locationDetails,
        photos = photos.map { it ?: "" },
        offers = offers?.map {
            it.toDomain()
        } ?: emptyList()
    )
}

@OptIn(ExperimentalUuidApi::class)
fun Category.toDomain(): org.example.project.domain.entity.Category {
    return org.example.project.domain.entity.Category(
        id = categoryId,
        title = categoryName,
        color = categoryColor
    )
}

@OptIn(ExperimentalUuidApi::class)
fun OffersItem.toDomain(): CraftsmanOffer {
    return CraftsmanOffer(
        id = id,
        craftsmanId = craftsmanId,
        customerId = customerId,
        customerIssueId = customerIssueId,
        price = price,
        createdDate = createdDate,
        visitedDate = visitedDate,
        message = message,
        isSelected = isSelected,
    )
}
