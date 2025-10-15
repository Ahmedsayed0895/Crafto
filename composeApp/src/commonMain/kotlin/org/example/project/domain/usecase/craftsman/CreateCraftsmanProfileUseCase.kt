package org.example.project.domain.usecase.craftsman

import org.example.project.domain.entity.PersonalInfo
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository


class CreateCraftsmanProfileUseCase(
    private val repository: CraftsmanRepository
) {
    suspend operator fun invoke(
        personalInfo: PersonalInfo,
        categories: List<String>
    ): String {
        // Business validation - throw ValidationException for any invalid input

        // Validate categories
        if (categories.isEmpty()) {
            throw ValidationException("Please select at least one service category")
        }

        // Validate personal info
        if (personalInfo.firstName.isBlank()) {
            throw ValidationException("First name is required")
        }

        if (personalInfo.lastName.isBlank()) {
            throw ValidationException("Last name is required")
        }

        if (personalInfo.phoneNumber.isBlank()) {
            throw ValidationException("Phone number is required")
        }

        if (!isValidPhoneNumber(personalInfo.phoneNumber)) {
            throw ValidationException("Please enter a valid phone number")
        }

        if (personalInfo.address.isBlank()) {
            throw ValidationException("Address is required")
        }

        // All validation passed - call repository
        // No try-catch needed - let exceptions propagate to ViewModel
        return repository.createCraftsmanProfile(personalInfo, categories)
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        // Basic phone validation - accepts international format
        return phone.matches(Regex("^\\+?[1-9]\\d{1,14}$"))
    }
}