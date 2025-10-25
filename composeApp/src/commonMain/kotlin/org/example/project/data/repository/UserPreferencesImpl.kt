package org.example.project.data.repository

import org.example.project.data.local.datasource.StorageLocalDataSource
import org.example.project.domain.repository.UserPreferences

class UserPreferencesImpl(
    private val storage: StorageLocalDataSource
) : UserPreferences {

    companion object {
        private const val KEY_USER_ID = "user_id"
    }

    override suspend fun getUserId(): String? {
        return storage.getString(KEY_USER_ID)
    }

    override suspend fun setUserId(userId: String) {
        storage.saveString(KEY_USER_ID, userId)
    }

    override suspend fun clearUserId() {
        storage.remove(KEY_USER_ID)
    }
}