package org.example.project.data.datasource.local

import org.example.project.data.model.CraftsmanPortfolioLocalDto

interface PortfolioLocalDataSource {
    suspend fun savePortfolioDraft(userId: String, portfolio: CraftsmanPortfolioLocalDto)
    suspend fun getPortfolioDraft(userId: String): CraftsmanPortfolioLocalDto?
    suspend fun clearPortfolioDraft(userId: String)
    suspend fun getAllPortfolioDrafts(): Map<String, CraftsmanPortfolioLocalDto>
}