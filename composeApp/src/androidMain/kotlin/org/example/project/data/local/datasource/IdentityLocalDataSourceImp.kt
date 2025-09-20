package org.example.project.data.local.datasource

import kotlinx.serialization.json.Json
import org.example.project.data.datasource.local.IdentityLocalDataSource
import org.example.project.data.datasource.local.StorageLocalDataSource
import org.example.project.data.model.CraftsmanIdentityLocalDto
import org.example.project.domain.entity.VerificationStatus

class IdentityLocalDataSourceImp (
    private val localStorage: StorageLocalDataSource,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
) : IdentityLocalDataSource {
    override suspend fun saveIdentityDraft(
        userId: String,
        identity: CraftsmanIdentityLocalDto
    ) {
        val key = "$IDENTITY_PREFIX$userId"
        val jsonString = json.encodeToString(identity)
        localStorage.saveString(key, jsonString)
    }

    override suspend fun getIdentityDraft(userId: String): CraftsmanIdentityLocalDto? {
        val key = "$IDENTITY_PREFIX$userId"
        val jsonString = localStorage.getString(key) ?: return null

        return try {
            json.decodeFromString<CraftsmanIdentityLocalDto>(jsonString)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun clearIdentityDraft(userId: String) {
        localStorage.remove("$IDENTITY_PREFIX$userId")
    }

    override suspend fun updateVerificationStatus(
        userId: String,
        status: VerificationStatus
    ) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val IDENTITY_PREFIX = "identity_draft_"
    }

}