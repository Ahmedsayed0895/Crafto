package org.example.project.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.project.data.datasource.local.IdentityLocalDataSource
import org.example.project.data.datasource.remote.StorageRemoteDataSource
import org.example.project.data.mapper.toDomain
import org.example.project.data.mapper.toLocalDto
import org.example.project.domain.entity.CraftsmanIdentity
import org.example.project.domain.entity.VerificationStatus
import org.example.project.domain.repository.DocumentType
import org.example.project.domain.repository.IdentityRepository

class IdentityRepositoryImp (
    private val remoteDataSource: StorageRemoteDataSource,
    private val localDataSource: IdentityLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IdentityRepository {
    override suspend fun uploadIdentityDocument(
        localPath: String,
        userId: String,
        type: DocumentType
    ): Result<String> = withContext(ioDispatcher) {
        try {
            // Get current draft or create new one
            val currentDraft = localDataSource.getIdentityDraft(userId)?.toDomain()
                ?: CraftsmanIdentity()

            // Update status to pending upload
            val draftWithPendingStatus = currentDraft.copy(
                verificationStatus = VerificationStatus.PENDING_UPLOAD
            )
            localDataSource.saveIdentityDraft(userId, draftWithPendingStatus.toLocalDto())

            // Upload file
            val fileName = "${type.name.lowercase()}_${System.currentTimeMillis()}_${userId}.jpg"
            val remotePath = "craftsmen/$userId/$IDENTITY_FOLDER/$fileName"
            val downloadUrl = remoteDataSource.uploadFile(localPath, remotePath)

            // Update draft with uploaded URL
            val updatedIdentity = when (type) {
                DocumentType.FRONT -> currentDraft.copy(
                    frontIdUrl = downloadUrl,
                    verificationStatus = if (currentDraft.backIdUrl != null) {
                        VerificationStatus.SUBMITTED
                    } else {
                        VerificationStatus.PENDING_UPLOAD
                    }
                )
                DocumentType.BACK -> currentDraft.copy(
                    backIdUrl = downloadUrl,
                    verificationStatus = if (currentDraft.frontIdUrl != null) {
                        VerificationStatus.SUBMITTED
                    } else {
                        VerificationStatus.PENDING_UPLOAD
                    }
                )
            }

            localDataSource.saveIdentityDraft(userId, updatedIdentity.toLocalDto())
            Result.success(downloadUrl)
        } catch (e: Exception) {
            // Revert status on failure
            val draft = localDataSource.getIdentityDraft(userId)?.toDomain()
                ?: CraftsmanIdentity()
            localDataSource.saveIdentityDraft(
                userId,
                draft.copy(verificationStatus = VerificationStatus.NOT_SUBMITTED).toLocalDto()
            )
            Result.failure(e)
        }
    }

    override suspend fun updateVerificationStatus(
        userId: String,
        status: VerificationStatus
    ): Result<Unit> = withContext(ioDispatcher) {
        try {
            val currentDraft = localDataSource.getIdentityDraft(userId)?.toDomain()
                ?: CraftsmanIdentity()

            val updatedDraft = currentDraft.copy(verificationStatus = status)
            localDataSource.saveIdentityDraft(userId, updatedDraft.toLocalDto())

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getIdentityVerification(userId: String): Result<CraftsmanIdentity?> {
        return try {
            val dto = localDataSource.getIdentityDraft(userId)
            Result.success(dto?.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteIdentityDocuments(userId: String): Result<Unit>  =
        withContext(ioDispatcher) {
            try {
                val identity = localDataSource.getIdentityDraft(userId)?.toDomain()

                // Delete files from Firebase Storage
                identity?.let {
                    it.frontIdUrl?.let { url ->
                        val remotePath = extractFirebaseStoragePath(url)
                        remoteDataSource.deleteFile(remotePath)
                    }

                    it.backIdUrl?.let { url ->
                        val remotePath = extractFirebaseStoragePath(url)
                        remoteDataSource.deleteFile(remotePath)
                    }
                }

                // Clear local data
                localDataSource.clearIdentityDraft(userId)

                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    override suspend fun saveDraft(
        userId: String,
        identity: CraftsmanIdentity
    ): Result<Unit> {
        return try {
            localDataSource.saveIdentityDraft(userId, identity.toLocalDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDraft(userId: String): Result<CraftsmanIdentity?> {
        return try {
            val dto = localDataSource.getIdentityDraft(userId)
            Result.success(dto?.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun clearDraft(userId: String): Result<Unit> {
        return try {
            localDataSource.clearIdentityDraft(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        private const val IDENTITY_FOLDER = "identity"
    }

    private fun extractFirebaseStoragePath(url: String): String {
        // Extract path from Firebase Storage URL
        val regex = """/o/(.+?)\?""".toRegex()
        val match = regex.find(url)
        return match?.groupValues?.get(1)?.replace("%2F", "/")
            ?: throw IllegalArgumentException("Invalid Firebase Storage URL")
    }
}