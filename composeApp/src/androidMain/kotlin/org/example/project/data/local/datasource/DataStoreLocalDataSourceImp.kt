package org.example.project.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "crafto_preferences"
)

@Single
class StorageLocalDataSourceImpl(
    private val context: Context
) : StorageLocalDataSource {

    private val dataStore = context.dataStore

    override suspend fun saveString(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        val preferencesKey = stringPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[preferencesKey]
        }.first()
    }

    override suspend fun saveStringSet(key: String, values: Set<String>) {
        val preferencesKey = stringSetPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = values
        }
    }

    override suspend fun getStringSet(key: String): Set<String>? {
        val preferencesKey = stringSetPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[preferencesKey]
        }.first()
    }

    override suspend fun saveLong(key: String, value: Long) {
        val preferencesKey = longPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getLong(key: String): Long? {
        val preferencesKey = longPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[preferencesKey]
        }.first()
    }

    override suspend fun saveBoolean(key: String, value: Boolean) {
        val preferencesKey = booleanPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val preferencesKey = booleanPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[preferencesKey]
        }.first()
    }

    override suspend fun remove(key: String) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
            preferences.remove(intPreferencesKey(key))
            preferences.remove(longPreferencesKey(key))
            preferences.remove(booleanPreferencesKey(key))
            preferences.remove(stringSetPreferencesKey(key))
        }
    }

    override suspend fun removeAll() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    override suspend fun getAllKeys(): Set<String> {
        return dataStore.data.map { preferences ->
            preferences.asMap().keys.map { it.name }.toSet()
        }.first()
    }
}