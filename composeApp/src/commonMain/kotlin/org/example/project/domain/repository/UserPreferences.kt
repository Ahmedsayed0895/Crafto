package org.example.project.domain.repository

interface UserPreferences {
    suspend fun getUserId(): String?
    suspend fun setUserId(userId: String)
    suspend fun clearUserId()
}