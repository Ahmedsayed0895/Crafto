package org.example.project.domain.usecase.session

import org.example.project.domain.entity.UserType
import org.example.project.domain.repository.UserPreferences

class SaveUserTypeUseCase(
    private val userPreferences: UserPreferences
) {
    suspend operator fun invoke(userType: UserType) {
        userPreferences.setUserType(userType)
    }
}