package org.example.project.data.repository.mapper
import org.example.project.data.dto.CustomerRequestsDtoItem
import org.example.project.domain.entity.CustomerRequest
import toDomain
import toDto

fun CustomerRequestsDtoItem.toDomain(): CustomerRequest{
    return CustomerRequest(
        customerId = customerId?.toLong()?:0L,
        requestStatus = "",
        issueTitle = issueTitle ?: "",
        category = category.let {
            it?.toDomain() ?: org.example.project.domain.entity.Category(0,"","")
        },
    )
}

fun CustomerRequest.toDto(): CustomerRequestsDtoItem{
    return CustomerRequestsDtoItem(
        customerId = customerId.toString(),
        issueTitle = issueTitle,
        category = category.toDto(),
    )

}

