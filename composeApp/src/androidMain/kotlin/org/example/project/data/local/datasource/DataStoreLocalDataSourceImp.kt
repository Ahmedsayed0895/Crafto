package org.example.project.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.example.project.data.datasource.local.StorageLocalDataSource


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "crafto_local_storage"
)

class DataStoreLocalDataSourceImp(private val context: Context)
    : StorageLocalDataSource {

    private val dataStore = context.dataStore

    override suspend fun saveString(key: String, value: String) {
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { prefs ->
            prefs[prefKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        return dataStore.data.map { prefs ->
            prefs[prefKey]
        }.first()
    }

    override suspend fun saveStringSet(key: String, values: Set<String>) {
        val prefKey = stringSetPreferencesKey(key)
        dataStore.edit { prefs ->
            prefs[prefKey] = values
        }
    }

    override suspend fun getStringSet(key: String): Set<String>? {
        val prefKey = stringSetPreferencesKey(key)
        return dataStore.data.map { prefs ->
            prefs[prefKey]
        }.first()
    }

    override suspend fun saveLong(key: String, value: Long) {
        val prefKey = longPreferencesKey(key)
        dataStore.edit { prefs ->
            prefs[prefKey] = value
        }
    }

    override suspend fun getLong(key: String): Long? {
        val prefKey = longPreferencesKey(key)
        return dataStore.data.map { prefs ->
            prefs[prefKey]
        }.first()
    }

    override suspend fun saveBoolean(key: String, value: Boolean) {
        val prefKey = booleanPreferencesKey(key)
        dataStore.edit { prefs ->
            prefs[prefKey] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.data.map { prefs ->
            prefs[prefKey]
        }.first()
    }

    override suspend fun remove(key: String) {
        dataStore.edit { prefs ->
            prefs.remove(stringPreferencesKey(key))
        }
    }

    override suspend fun removeAll() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    override suspend fun getAllKeys(): Set<String> {
        return dataStore.data.map { prefs ->
            prefs.asMap().keys.map { it.name }.toSet()
        }.first()
    }
}