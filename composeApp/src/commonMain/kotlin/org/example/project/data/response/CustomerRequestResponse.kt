package org.example.project.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.data.dto.CustomerRequestsDtoItem

@Serializable
data class CustomerRequestsResponse(

    @SerialName("CustomerRequestsResponse")
    val customerRequestsResponse: List<CustomerRequestsDtoItem?>? = null
)
