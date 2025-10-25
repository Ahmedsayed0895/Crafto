package org.example.project.domain.repository

import org.example.project.domain.entity.CustomerRequest
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
interface CustomerRequestRepository {

    suspend fun getCustomersRequests(customerRequestId: String): List<CustomerRequest>
}