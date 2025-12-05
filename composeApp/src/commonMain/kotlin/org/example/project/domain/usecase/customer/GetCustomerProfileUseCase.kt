package org.example.project.domain.usecase.customer

import org.example.project.domain.entity.CustomerProfile
import org.example.project.domain.repository.CustomerRepository

class GetCustomerProfileUseCase(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(): CustomerProfile {
        return repository.getCustomerProfile()
    }
}