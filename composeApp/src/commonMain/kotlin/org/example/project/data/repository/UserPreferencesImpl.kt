package org.example.project.data.repository

import org.example.project.data.local.datasource.StorageLocalDataSource
import org.example.project.domain.entity.UserType
import org.example.project.domain.repository.UserPreferences

class UserPreferencesImpl(
    private val storage: StorageLocalDataSource
) : UserPreferences {

    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_TYPE = "user_type"
        private const val KEY_IS_FIRST_TIME = "is_first_time"
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

    override suspend fun getUserType(): UserType? {
        return storage.getString(KEY_USER_TYPE)?.let {
            try {
                UserType.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }

    override suspend fun setUserType(userType: UserType) {
        storage.saveString(KEY_USER_TYPE, userType.name)
    }

    override suspend fun clearUserType() {
        storage.remove(KEY_USER_TYPE)
    }

    override suspend fun isFirstTime(): Boolean {
        return storage.getBoolean(KEY_IS_FIRST_TIME) ?: true
    }

    override suspend fun setFirstTime(isFirstTime: Boolean) {
        storage.saveBoolean(KEY_IS_FIRST_TIME, isFirstTime)
    }
}