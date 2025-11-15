package org.example.project.domain.usecase.session

import org.example.project.domain.entity.UserSession
import org.example.project.domain.repository.UserPreferences

class GetUserSessionUseCase(
    private val userPreferences: UserPreferences
) {
    suspend operator fun invoke(): UserSession {
        return UserSession(
            userId = userPreferences.getUserId(),
            userType = userPreferences.getUserType(),
            isFirstTime = userPreferences.isFirstTime()
        )
    }
}