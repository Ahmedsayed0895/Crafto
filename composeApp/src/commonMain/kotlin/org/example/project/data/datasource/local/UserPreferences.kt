package org.example.project.data.datasource.local

interface UserPreferences {
    suspend fun getUserId(): String?
    suspend fun setUserId(userId: String)
    suspend fun clearUserId()
}