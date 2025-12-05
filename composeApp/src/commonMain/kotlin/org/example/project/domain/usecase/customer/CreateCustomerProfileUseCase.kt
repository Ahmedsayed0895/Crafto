package org.example.project.domain.usecase.customer

import org.example.project.domain.entity.CustomerLocation
import org.example.project.domain.entity.CustomerPersonalInfo
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CustomerRepository
import org.example.project.domain.service.ValidationService
import org.example.project.domain.util.AppConstants
import org.example.project.domain.util.AppConstants.Categories.MAX_CATEGORIES

class CreateCustomerProfileUseCase(
    private val repository: CustomerRepository,
    private val validationService: ValidationService
) {
    suspend operator fun invoke(
        customerPersonalInfo: CustomerPersonalInfo,
        categories: List<String>,
        location: CustomerLocation
    ): String {
        if (categories.size < AppConstants.Categories.MIN_CATEGORIES) {
            throw ValidationException("Please select at least one service category")
        }

        if (categories.size > MAX_CATEGORIES) {
            throw ValidationException("Please select at most $MAX_CATEGORIES service categories")
        }

        if (customerPersonalInfo.name.length < AppConstants.PersonalInfo.MIN_FIRST_NAME_LENGTH) {
            throw ValidationException("First name is required")
        }

        if (!validationService.isValidPhoneNumber(customerPersonalInfo.phoneNumber)) {
            throw ValidationException("Invalid phone number format")
        }

        return repository.createCustomerProfile(
            personalInfo = customerPersonalInfo,
            categories = categories,
            location = location
        )
    }
}