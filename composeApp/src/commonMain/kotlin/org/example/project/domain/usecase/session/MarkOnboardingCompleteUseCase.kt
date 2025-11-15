package org.example.project.domain.usecase.session

import org.example.project.domain.repository.UserPreferences

class MarkOnboardingCompleteUseCase(
    private val userPreferences: UserPreferences
) {
    suspend operator fun invoke() {
        userPreferences.setFirstTime(false)
    }
}