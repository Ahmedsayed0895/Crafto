package org.example.project.domain.usecase.customer

import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CustomerRepository

class DeleteCustomerAccountUseCase(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(
        customerId: String,
        confirmDelete: Boolean = false
    ): Boolean {
        if (customerId.isBlank()) {
            throw ValidationException("Customer ID is required")
        }

        if (!confirmDelete) {
            throw ValidationException("Please confirm account deletion")
        }

        return repository.deleteCustomerAccount(customerId)
    }

}