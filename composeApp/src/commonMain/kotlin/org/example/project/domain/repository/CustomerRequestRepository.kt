package org.example.project.domain.repository

import org.example.project.domain.entity.CustomerRequest
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface CustomerRequestRepository {

    suspend fun getCustomersRequests(customerRequestId: Uuid): List<CustomerRequest>
}