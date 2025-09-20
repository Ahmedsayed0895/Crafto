package org.example.project.domain.repository

import org.example.project.domain.entity.CraftsmanIdentity
import org.example.project.domain.entity.VerificationStatus

interface IdentityRepository {
    suspend fun uploadIdentityDocument(localPath: String, userId: String, type: DocumentType): Result<String>
    suspend fun updateVerificationStatus(userId: String, status: VerificationStatus): Result<Unit>
    suspend fun getIdentityVerification(userId: String): Result<CraftsmanIdentity?>
    suspend fun deleteIdentityDocuments(userId: String): Result<Unit>
    suspend fun saveDraft(userId: String, identity: CraftsmanIdentity): Result<Unit>
    suspend fun getDraft(userId: String): Result<CraftsmanIdentity?>
    suspend fun clearDraft(userId: String): Result<Unit>
}

enum class DocumentType {
    FRONT,
    BACK
}