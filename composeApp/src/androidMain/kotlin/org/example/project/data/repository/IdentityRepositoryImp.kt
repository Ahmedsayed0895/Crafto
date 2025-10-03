package org.example.project.data.repository

import androidx.core.net.toUri
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
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class IdentityRepositoryImp (
    private val remoteDataSource: StorageRemoteDataSource,
    private val localDataSource: IdentityLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IdentityRepository {

    companion object {
        private const val IDENTITY_FOLDER = "identity"
    }

    override suspend fun uploadIdentityDocument(
        localPath: String,
        userId: String,
        type: DocumentType
    ): Result<String> = withContext(ioDispatcher) {
        // Save original state for rollback
        val originalDraft = localDataSource.getIdentityDraft(userId)?.toDomain()
            ?: CraftsmanIdentity()

        try {
            // Update status to indicate upload in progress
            val uploadingDraft = originalDraft.copy(
                verificationStatus = VerificationStatus.PENDING_UPLOAD
            )
            localDataSource.saveIdentityDraft(userId, uploadingDraft.toLocalDto())

            // Upload to Firebase
            val fileName = "${type.name.lowercase()}_${System.currentTimeMillis()}.jpg"
            val remotePath = "craftsmen/$userId/$IDENTITY_FOLDER/$fileName"
            val downloadUrl = remoteDataSource.uploadFile(localPath, remotePath)

            // Update draft with successful upload
            val updatedIdentity = when (type) {
                DocumentType.FRONT -> originalDraft.copy(
                    frontIdUrl = downloadUrl,
                    verificationStatus = if (originalDraft.backIdUrl != null) {
                        VerificationStatus.SUBMITTED
                    } else {
                        VerificationStatus.PENDING_UPLOAD
                    }
                )
                DocumentType.BACK -> originalDraft.copy(
                    backIdUrl = downloadUrl,
                    verificationStatus = if (originalDraft.frontIdUrl != null) {
                        VerificationStatus.SUBMITTED
                    } else {
                        VerificationStatus.PENDING_UPLOAD
                    }
                )
            }

            localDataSource.saveIdentityDraft(userId, updatedIdentity.toLocalDto())

            // TODO: Submit to Spring Boot API when ready
            // apiDataSource.submitIdentityVerification(userId, frontUrl, backUrl)

            Result.success(downloadUrl)

        } catch (e: Exception) {
            // Rollback to original state on failure
            localDataSource.saveIdentityDraft(userId, originalDraft.toLocalDto())
            Result.failure(e)
        }
    }

    // Simple local operation - no withContext needed
    override suspend fun updateVerificationStatus(
        userId: String,
        status: VerificationStatus
    ): Result<Unit> {
        return try {
            val currentDraft = localDataSource.getIdentityDraft(userId)?.toDomain()
                ?: CraftsmanIdentity()

            val updatedDraft = currentDraft.copy(verificationStatus = status)
            localDataSource.saveIdentityDraft(userId, updatedDraft.toLocalDto())

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // TODO: Refactor to use Spring Boot API
    override suspend fun getIdentityVerification(
        userId: String
    ): Result<CraftsmanIdentity?> = withContext(ioDispatcher) {
        try {
            // TEMPORARY: Check Firebase Storage for uploaded files
            val identityFolder = "craftsmen/$userId/$IDENTITY_FOLDER"
            val files = remoteDataSource.listFiles(identityFolder)

            val frontFile = files.find { it.name.contains("front") }
            val backFile = files.find { it.name.contains("back") }
            if (frontFile == null && backFile == null) {
                // No identity files uploaded yet
                return@withContext Result.success(null)
            }

            // Get local draft for additional metadata if needed
            val localDraft = localDataSource.getIdentityDraft(userId)?.toDomain()

            val identity = CraftsmanIdentity(
                frontIdUrl = frontFile?.url,
                backIdUrl = backFile?.url,
                verificationStatus = when {
                    frontFile != null && backFile != null -> VerificationStatus.SUBMITTED
                    frontFile != null || backFile != null -> VerificationStatus.PENDING_UPLOAD
                    else -> VerificationStatus.NOT_SUBMITTED
                }
            )

            Result.success(identity)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteIdentityDocuments(userId: String): Result<Unit> =
        withContext(ioDispatcher) {
            try {
                val identity = localDataSource.getIdentityDraft(userId)?.toDomain()

                // Delete files from Firebase Storage
                identity?.let {
                    // Delete front ID if exists
                    it.frontIdUrl?.let { url ->
                        try {
                            val remotePath = extractFirebaseStoragePath(url)
                            remoteDataSource.deleteFile(remotePath)
                        } catch (e: Exception) {
                            // Log error but continue with deletion process
                            // You might want to add proper logging here
                        }
                    }

                    // Delete back ID if exists
                    it.backIdUrl?.let { url ->
                        try {
                            val remotePath = extractFirebaseStoragePath(url)
                            remoteDataSource.deleteFile(remotePath)
                        } catch (e: Exception) {
                            // Log error but continue with deletion process
                        }
                    }
                }

                // Clear local data regardless of remote deletion success
                localDataSource.clearIdentityDraft(userId)

                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    // Simple local operations - no withContext needed
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

    private fun extractFirebaseStoragePath(url: String): String {
        val uri = url.toUri()
        val encodedPath = uri.path
            ?: throw IllegalArgumentException("Invalid Firebase Storage URL: no path found")

        val index = encodedPath.indexOf("/o/")
        if (index == -1) {
            throw IllegalArgumentException("Invalid Firebase Storage URL: missing /o/ segment")
        }

        val encodedPart = encodedPath.substring(index + 3)
        return URLDecoder.decode(encodedPart, StandardCharsets.UTF_8.name())
    }
}