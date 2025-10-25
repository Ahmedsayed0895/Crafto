package org.example.project.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import org.example.project.data.dto.CustomerRequestDto
import org.example.project.data.mapper.toDomain
import org.example.project.data.utils.NetworkConstants.CUSTOMER_REQUESTS_END_POINT
import org.example.project.data.utils.safeApiCall
import org.example.project.domain.entity.CustomerRequest
import org.example.project.domain.repository.CustomerRequestRepository
import org.koin.core.annotation.Provided
import org.koin.core.annotation.Single

@Single(binds = [CustomerRequestRepository::class])
class CustomerRequestRepositoryImpl(
    @Provided private val client: HttpClient
) : CustomerRequestRepository {

    override suspend fun getCustomersRequests(customerId: String): List<CustomerRequest> {
        return safeApiCall<List<CustomerRequestDto>>{
            client.get("$CUSTOMER_REQUESTS_END_POINT/$customerId")
        }.map { it.toDomain() }
    }

}