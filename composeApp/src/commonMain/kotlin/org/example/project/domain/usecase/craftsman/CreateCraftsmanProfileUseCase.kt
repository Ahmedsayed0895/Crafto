package org.example.project.domain.usecase.craftsman

import org.example.project.domain.entity.CraftsmanPersonalInfo
import org.example.project.domain.exception.ValidationException
import org.example.project.domain.repository.CraftsmanRepository
import org.example.project.domain.service.ValidationService
import org.example.project.domain.util.AppConstants
import org.example.project.domain.util.AppConstants.Categories.MAX_CATEGORIES


class CreateCraftsmanProfileUseCase(
    private val repository: CraftsmanRepository,
    private val validationService: ValidationService
) {
    suspend operator fun invoke(
        craftsmanPersonalInfo: CraftsmanPersonalInfo,
        categories: List<String>
    ): String {
        if (categories.size < AppConstants.Categories.MIN_CATEGORIES) {
            throw ValidationException("Please select at least one service category")
        }

        if (categories.size > MAX_CATEGORIES) {
            throw ValidationException("Please select at most $MAX_CATEGORIES service categories")
        }

        if (craftsmanPersonalInfo.firstName.length < AppConstants.PersonalInfo.MIN_FIRST_NAME_LENGTH) {
            throw ValidationException("First name is required")
        }

        if (craftsmanPersonalInfo.lastName.length < AppConstants.PersonalInfo.MIN_LAST_NAME_LENGTH) {
            throw ValidationException("Last name is required")
        }

        if (craftsmanPersonalInfo.phoneNumber.isBlank()) {
            throw ValidationException("Phone number is required")
        }

        if (!validationService.isValidPhoneNumber(craftsmanPersonalInfo.phoneNumber)) {
            throw ValidationException("Please enter a valid phone number")
        }

        if (craftsmanPersonalInfo.address.length < AppConstants.PersonalInfo.MIN_ADDRESS_LENGTH) {
            throw ValidationException("Address is required")
        }

        return repository.createCraftsmanProfile(craftsmanPersonalInfo, categories)
    }
}