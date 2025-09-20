package org.example.project.data.local.datasource

import kotlinx.serialization.json.Json
import org.example.project.data.datasource.local.PortfolioLocalDataSource
import org.example.project.data.datasource.local.StorageLocalDataSource
import org.example.project.data.model.CraftsmanPortfolioLocalDto

class PortfolioLocalDataSourceImp(
    private val localStorage: StorageLocalDataSource,
    private val json: Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
) : PortfolioLocalDataSource {
    override suspend fun savePortfolioDraft(
        userId: String,
        portfolio: CraftsmanPortfolioLocalDto
    ) {
        val key = "$PORTFOLIO_PREFIX$userId"
        val jsonString = json.encodeToString(portfolio)
        localStorage.saveString(key, jsonString)
    }

    override suspend fun getPortfolioDraft(userId: String): CraftsmanPortfolioLocalDto? {
        val key = "$PORTFOLIO_PREFIX$userId"
        val jsonString = localStorage.getString(key) ?: return null

        return try {
            json.decodeFromString<CraftsmanPortfolioLocalDto>(jsonString)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun clearPortfolioDraft(userId: String) {
        localStorage.remove("$PORTFOLIO_PREFIX$userId")
    }

    override suspend fun getAllPortfolioDrafts(): Map<String, CraftsmanPortfolioLocalDto> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val PORTFOLIO_PREFIX = "portfolio_draft_"
    }
}