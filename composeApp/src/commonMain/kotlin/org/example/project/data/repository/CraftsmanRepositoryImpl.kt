package org.example.project.data.repository

import org.example.project.data.remote.dto.CreateCraftsmanRequest
import org.example.project.domain.repository.UserPreferences
import org.example.project.data.mapper.toDomain
import org.example.project.data.mapper.toDto
import org.example.project.data.datasource.remote.CraftsmanRemoteDataSource
import org.example.project.domain.entity.Craftsman
import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.entity.PersonalInfo
import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.exception.ApiException
import org.example.project.domain.exception.UnauthorizedException
import org.example.project.domain.model.WorkImage
import org.example.project.domain.repository.CraftsmanRepository
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class CraftsmanRepositoryImpl (
    private val remoteDataSource: CraftsmanRemoteDataSource,
    private val userPreferences: UserPreferences
) : CraftsmanRepository {
    @OptIn(ExperimentalTime::class)
    override suspend fun createCraftsmanProfile(
        personalInfo: PersonalInfo,
        categories: List<String>
    ): String {
        var userId = userPreferences.getUserId()
            //?: throw UnauthorizedException("Session must be created to create craftsman profile")

        if (userId.isNullOrBlank()) {
            userId = "temp-user-${Clock.System.now()}"
            println("⚠️ Using temporary userId: $userId (remove after OTP integration)")
            userPreferences.setUserId(userId)
        }

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

    override suspend fun uploadProfilePicture(
        craftsmanId: String,
        profilePicture: ByteArray,
        profilePictureFileName: String
    ): String {
        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException("User must be logged in to upload profile picture")

        val response = remoteDataSource.uploadProfilePicture(
            userId = userId,
            craftsmanId = craftsmanId,
            profilePicture = profilePicture,
            profilePictureFileName = profilePictureFileName
        )
        return response.profilePictureUrl
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