package org.example.project.data.repository

import android.net.Uri
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
import androidx.core.net.toUri
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

class PortfolioRepositoryImp(
    private val remoteDataSource: StorageRemoteDataSource,
    private val localDataSource: PortfolioLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PortfolioRepository {

    companion object {
        private const val PORTFOLIO_FOLDER = "portfolio"
        private const val MAX_PORTFOLIO_PHOTOS = 10
    }

    override suspend fun uploadPortfolioPhotos(
        localPaths: List<String>,
        userId: String
    ): Result<List<String>> = withContext(ioDispatcher) {
        // Validate input
        if (localPaths.isEmpty()) {
            return@withContext Result.success(emptyList())
        }

        if (localPaths.size > MAX_PORTFOLIO_PHOTOS) {
            return@withContext Result.failure(
                IllegalArgumentException("Maximum $MAX_PORTFOLIO_PHOTOS photos allowed")
            )
        }

        // Save original state for potential rollback
        val originalDraft = localDataSource.getPortfolioDraft(userId)?.toDomain()

        try {
            // Save draft with local paths
            val draft = CraftsmanPortfolio(photoPaths = localPaths)
            localDataSource.savePortfolioDraft(userId, draft.toLocalDto())

            // Upload photos in parallel for better performance
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
                description = originalDraft?.description ?: "",
                photoPaths = emptyList() // Clear local paths after successful upload
            )
            localDataSource.savePortfolioDraft(userId, updatedDraft.toLocalDto())

            // TODO: Submit to Spring Boot API when ready
            // apiDataSource.submitPortfolio(userId, uploadedUrls, description)

            Result.success(uploadedUrls)

        } catch (e: Exception) {
            // Rollback to original state on failure
            if (originalDraft != null) {
                localDataSource.savePortfolioDraft(userId, originalDraft.toLocalDto())
            } else {
                // If no original draft, clear the failed attempt
                localDataSource.clearPortfolioDraft(userId)
            }
            Result.failure(e)
        }
    }

    // No withContext needed - simple local operation
    override suspend fun updatePortfolioDescription(
        userId: String,
        description: String
    ): Result<Unit> {
        return try {
            val currentDraft = localDataSource.getPortfolioDraft(userId)?.toDomain()
                ?: CraftsmanPortfolio()

            val updatedDraft = currentDraft.copy(description = description.trim())
            localDataSource.savePortfolioDraft(userId, updatedDraft.toLocalDto())

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // TODO: Refactor to use Spring Boot API
    override suspend fun getPortfolio(userId: String): Result<CraftsmanPortfolio?> =
        withContext(ioDispatcher) {
            try {
                // TEMPORARY: Get directly from Firebase Storage
                val folderPath = "craftsmen/$userId/$PORTFOLIO_FOLDER"
                val files = remoteDataSource.listFiles(folderPath)

                if (files.isEmpty()) {
                    return@withContext Result.success(null)
                }

                // Get URLs from Firebase Storage
                val photoUrls = files
                    .sortedBy { it.name } // Ensure consistent ordering
                    .map { it.url }

                // For now, get description from local draft (if exists)
                // In future, this will come from Spring Boot
                val localDraft = localDataSource.getPortfolioDraft(userId)?.toDomain()

                val portfolio = CraftsmanPortfolio(
                    photoUrls = photoUrls,
                    description = localDraft?.description ?: "",
                    photoPaths = emptyList()
                )

                Result.success(portfolio)
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

    // Simple local operations - no withContext needed
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