package org.example.project.domain.repository

import org.example.project.domain.entity.CraftsmanPortfolio

interface PortfolioRepository {
    suspend fun uploadPortfolioPhotos(localPaths: List<String>, userId: String): Result<List<String>>
    suspend fun updatePortfolioDescription(userId: String, description: String): Result<Unit>
    suspend fun getPortfolio(userId: String): Result<CraftsmanPortfolio?>
    suspend fun deletePortfolioPhoto(photoUrl: String, userId: String): Result<Unit>
    suspend fun saveDraft(userId: String, portfolio: CraftsmanPortfolio): Result<Unit>
    suspend fun getDraft(userId: String): Result<CraftsmanPortfolio?>
    suspend fun clearDraft(userId: String): Result<Unit>
}