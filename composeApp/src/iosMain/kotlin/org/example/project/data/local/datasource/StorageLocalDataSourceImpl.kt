package org.example.project.data.local.datasource

import org.koin.core.annotation.Single
import platform.Foundation.NSUserDefaults

@Single
class StorageLocalDataSourceImpl : StorageLocalDataSource {

    private val userDefaults = NSUserDefaults.standardUserDefaults

    override suspend fun saveString(key: String, value: String) {
        userDefaults.setObject(value, forKey = key)
    }

    override suspend fun getString(key: String): String? {
        return userDefaults.stringForKey(key)
    }

    override suspend fun saveStringSet(key: String, values: Set<String>) {
        val list = values.toList()
        userDefaults.setObject(list, forKey = key)
    }

    override suspend fun getStringSet(key: String): Set<String>? {
        val array = userDefaults.objectForKey(key) as? List<*>
        return array?.mapNotNull { it as? String }?.toSet()
    }

    override suspend fun saveLong(key: String, value: Long) {
        // NSUserDefaults uses NSInteger (Long on 64-bit platforms)
        userDefaults.setInteger(value, forKey = key)
    }

    override suspend fun getLong(key: String): Long? {
        return if (userDefaults.objectForKey(key) != null) {
            userDefaults.integerForKey(key)
        } else {
            null
        }
    }

    override suspend fun saveBoolean(key: String, value: Boolean) {
        userDefaults.setBool(value, forKey = key)
    }

    override suspend fun getBoolean(key: String): Boolean? {
        return if (userDefaults.objectForKey(key) != null) {
            userDefaults.boolForKey(key)
        } else {
            null
        }
    }

    override suspend fun remove(key: String) {
        userDefaults.removeObjectForKey(key)
    }

    override suspend fun removeAll() {
        // Get the app's bundle identifier to only remove app-specific keys
        val dictionary = userDefaults.dictionaryRepresentation()
        dictionary.keys.forEach { key ->
            (key as? String)?.let {
                userDefaults.removeObjectForKey(it)
            }
        }
        userDefaults.synchronize()
    }

    override suspend fun getAllKeys(): Set<String> {
        return userDefaults.dictionaryRepresentation().keys
            .mapNotNull { it as? String }
            .toSet()
    }
}