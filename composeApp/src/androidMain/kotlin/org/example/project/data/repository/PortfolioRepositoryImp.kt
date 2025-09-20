package org.example.project.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.example.project.data.datasource.local.PortfolioLocalDataSource
import org.example.project.data.datasource.remote.StorageRemoteDataSource
import org.example.project.data.mapper.toDomain
import org.example.project.data.mapper.toLocalDto
import org.example.project.domain.entity.CraftsmanPortfolio
import org.example.project.domain.repository.PortfolioRepository
import java.util.UUID

class PortfolioRepositoryImp(
    private val remoteDataSource: StorageRemoteDataSource,
    private val localDataSource: PortfolioLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PortfolioRepository {
    override suspend fun uploadPortfolioPhotos(
        localPaths: List<String>,
        userId: String
    ): Result<List<String>> = withContext(ioDispatcher) {
        try {
            // Validate
            if (localPaths.size > MAX_PORTFOLIO_PHOTOS) {
                return@withContext Result.failure(
                    IllegalArgumentException("Maximum $MAX_PORTFOLIO_PHOTOS photos allowed")
                )
            }

            // Save draft before upload
            val draft = CraftsmanPortfolio(photoPaths = localPaths)
            localDataSource.savePortfolioDraft(userId, draft.toLocalDto())

            // Upload photos in parallel
            val uploadedUrls = coroutineScope {
                localPaths.mapIndexed { index, path ->
                    async {
                        val fileName = "portfolio_${System.currentTimeMillis()}_${UUID.randomUUID()}_$index.jpg"
                        val remotePath = "craftsmen/$userId/$PORTFOLIO_FOLDER/$fileName"
                        remoteDataSource.uploadFile(path, remotePath)
                    }
                }.awaitAll()
            }

            // Update draft with uploaded URLs
            val updatedDraft = CraftsmanPortfolio(
                photoUrls = uploadedUrls,
                description = draft.description,
                photoPaths = emptyList() // Clear local paths after successful upload
            )
            localDataSource.savePortfolioDraft(userId, updatedDraft.toLocalDto())

            Result.success(uploadedUrls)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updatePortfolioDescription(
        userId: String,
        description: String
    ): Result<Unit>  = withContext(ioDispatcher) {
        try {
            val currentDraft = localDataSource.getPortfolioDraft(userId)?.toDomain()
                ?: CraftsmanPortfolio()

            val updatedDraft = currentDraft.copy(description = description.trim())
            localDataSource.savePortfolioDraft(userId, updatedDraft.toLocalDto())

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPortfolio(userId: String): Result<CraftsmanPortfolio?> {
        return try {
            val dto = localDataSource.getPortfolioDraft(userId)
            Result.success(dto?.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deletePortfolioPhoto(
        photoUrl: String,
        userId: String
    ): Result<Unit> = withContext(ioDispatcher) {
        try {
            // Extract path from URL
            val remotePath = extractFirebaseStoragePath(photoUrl)

            // Delete from Firebase
            val deleted = remoteDataSource.deleteFile(remotePath)
            if (!deleted) {
                return@withContext Result.failure(
                    Exception("Failed to delete photo from storage")
                )
            }

            // Update local draft
            val currentDraft = localDataSource.getPortfolioDraft(userId)?.toDomain()
            currentDraft?.let { draft ->
                val updatedDraft = draft.copy(
                    photoUrls = draft.photoUrls.filter { it != photoUrl }
                )
                localDataSource.savePortfolioDraft(userId, updatedDraft.toLocalDto())
            }

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveDraft(
        userId: String,
        portfolio: CraftsmanPortfolio
    ): Result<Unit> {
        return try {
            localDataSource.savePortfolioDraft(userId, portfolio.toLocalDto())
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getDraft(userId: String): Result<CraftsmanPortfolio?> {
        return try {
            val dto = localDataSource.getPortfolioDraft(userId)
            Result.success(dto?.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun clearDraft(userId: String): Result<Unit> {
        return try {
            localDataSource.clearPortfolioDraft(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        private const val PORTFOLIO_FOLDER = "portfolio"
        private const val MAX_PORTFOLIO_PHOTOS = 10
    }

    private fun extractFirebaseStoragePath(url: String): String {
        // Extract path from Firebase Storage URL
        // Format: https://firebasestorage.googleapis.com/v0/b/bucket/o/path%2Fto%2Ffile?alt=media
        val regex = """/o/(.+?)\?""".toRegex()
        val match = regex.find(url)
        return match?.groupValues?.get(1)?.replace("%2F", "/")
            ?: throw IllegalArgumentException("Invalid Firebase Storage URL")
    }
}