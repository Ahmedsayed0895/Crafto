package org.example.project.data.datasource.local

import org.example.project.data.model.CraftsmanIdentityLocalDto
import org.example.project.domain.entity.VerificationStatus

interface IdentityLocalDataSource {
    suspend fun saveIdentityDraft(userId: String, identity: CraftsmanIdentityLocalDto)
    suspend fun getIdentityDraft(userId: String): CraftsmanIdentityLocalDto?
    suspend fun clearIdentityDraft(userId: String)
    suspend fun updateVerificationStatus(userId: String, status: VerificationStatus)
}