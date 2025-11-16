package org.example.project.domain.repository

import org.example.project.domain.entity.UserType

interface UserPreferences {
    suspend fun getUserId(): String?
    suspend fun setUserId(userId: String)
    suspend fun clearUserId()

    //TODO: Separate UserType to its own Repository
    suspend fun getUserType(): UserType?
    suspend fun setUserType(userType: UserType)
    suspend fun clearUserType()

    // Add these
    suspend fun isFirstTime(): Boolean
    suspend fun setFirstTime(isFirstTime: Boolean)
}