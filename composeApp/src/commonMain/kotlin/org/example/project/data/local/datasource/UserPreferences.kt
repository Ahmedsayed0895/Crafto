package org.example.project.data.local.datasource

interface UserPreferences {
    suspend fun getUserId(): String?
    suspend fun setUserId(userId: String)
    suspend fun clearUserId()
}