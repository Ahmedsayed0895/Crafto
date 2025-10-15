package org.example.project.data.repository

import org.example.project.data.dto.CreateCraftsmanRequest
import org.example.project.data.local.datasource.UserPreferences
import org.example.project.data.mapper.toDomain
import org.example.project.data.mapper.toDto
import org.example.project.data.remote.datasource.CraftsmanRemoteDataSource
import org.example.project.domain.entity.Craftsman
import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.entity.PersonalInfo
import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.exception.ApiException
import org.example.project.domain.exception.UnauthorizedException
import org.example.project.domain.model.WorkImage
import org.example.project.domain.repository.CraftsmanRepository
import org.koin.core.annotation.Single

class CraftsmanRepositoryImpl (
    private val remoteDataSource: CraftsmanRemoteDataSource,
    private val userPreferences: UserPreferences
) : CraftsmanRepository {
    override suspend fun createCraftsmanProfile(
        personalInfo: PersonalInfo,
        categories: List<String>
    ): String {
        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException("User must be logged in to create craftsman profile")

        val request = CreateCraftsmanRequest(
            personalInfo = personalInfo.toDto(),
            categories = categories
        )

        val response = remoteDataSource.createCraftsmanProfile(userId, request)

        return response.craftsmanId
    }

    override suspend fun uploadIdCards(
        craftsmanId: String,
        idCardFront: ByteArray,
        idCardFrontFileName: String,
        idCardBack: ByteArray,
        idCardBackFileName: String
    ): VerificationDocuments {
        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException("User must be logged in to upload documents")

        val response = remoteDataSource.uploadIdCards(
            userId = userId,
            craftsmanId = craftsmanId,
            idCardFront = idCardFront,
            idCardFrontFileName = idCardFrontFileName,
            idCardBack = idCardBack,
            idCardBackFileName = idCardBackFileName
        )

        return VerificationDocuments(
            idCardFrontUrl = response.idCardFrontUrl,
            idCardBackUrl = response.idCardBackUrl,
            workPortfolioUrls = emptyList()
        )
    }

    override suspend fun uploadWorkPortfolio(
        craftsmanId: String,
        workImages: List<WorkImage>
    ): List<String> {
        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException()

        val response = remoteDataSource.uploadWorkPortfolio(
            userId,
            craftsmanId,
            workImages
        )

        return response.workImageUrls
    }

    override suspend fun getCraftsmanProfile(): Craftsman {
        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException()

        val response = remoteDataSource.getCraftsmanProfile(userId)
        return response.toDomain()
    }

    override suspend fun getCraftsmanStatus(craftsmanId: String): CraftsmanStatus {
        val response = remoteDataSource.getCraftsmanStatus(craftsmanId)
        return try {
            CraftsmanStatus.valueOf(response.status)
        } catch (e: IllegalArgumentException) {
            throw ApiException("Invalid craftsman status: ${response.status}")
        }
    }

    override suspend fun deleteCraftsmanAccount(craftsmanId: String) {

        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException()

        val response = remoteDataSource.deleteCraftsmanAccount(userId, craftsmanId)

        if (!response.success) {
            throw ApiException(response.message)
        }
        userPreferences.clearUserId()
    }

}