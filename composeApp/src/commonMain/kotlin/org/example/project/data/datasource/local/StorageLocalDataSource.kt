package org.example.project.data.datasource.local

interface StorageLocalDataSource {
    suspend fun saveString(key: String, value: String)
    suspend fun getString(key: String): String?
    suspend fun saveStringSet(key: String, values: Set<String>)
    suspend fun getStringSet(key: String): Set<String>?
    suspend fun saveLong(key: String, value: Long)
    suspend fun getLong(key: String): Long?
    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String): Boolean?
    suspend fun remove(key: String)
    suspend fun removeAll()
    suspend fun getAllKeys(): Set<String>
}