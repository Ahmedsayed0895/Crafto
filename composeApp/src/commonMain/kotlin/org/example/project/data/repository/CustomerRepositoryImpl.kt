package org.example.project.data.repository

import org.example.project.data.mapper.toDomain
import org.example.project.data.mapper.toDto
import org.example.project.data.remote.datasource.CustomerRemoteDataSource
import org.example.project.data.remote.dto.CreateCustomerRequest
import org.example.project.domain.entity.CustomerLocation
import org.example.project.domain.entity.CustomerPersonalInfo
import org.example.project.domain.entity.CustomerProfile
import org.example.project.domain.exception.ApiException
import org.example.project.domain.exception.UnauthorizedException
import org.example.project.domain.repository.CustomerRepository
import org.example.project.domain.repository.UserPreferences
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class CustomerRepositoryImpl(
    private val remoteDataSource: CustomerRemoteDataSource,
    private val userPreferences: UserPreferences
): CustomerRepository {
    @OptIn(ExperimentalTime::class)
    override suspend fun createCustomerProfile(
        personalInfo: CustomerPersonalInfo,
        categories: List<String>,
        location: CustomerLocation
    ): String {
        var userId = userPreferences.getUserId()
            //?: throw UnauthorizedException("Session must be created to create craftsman profile")

        if (userId.isNullOrBlank()) {
            userId = "temp-user-${Clock.System.now()}"
            println("⚠️ Using temporary userId: $userId (remove after OTP integration)")
            userPreferences.setUserId(userId)
        }

        val request = CreateCustomerRequest(
            personalInfo = personalInfo.toDto(),
            categories = categories,
            location = location.toDto()
        )

        val response = remoteDataSource.createCustomerProfile(userId, request)

        return response.customerId
    }

    override suspend fun uploadProfilePicture(
        customerId: String,
        profilePicture: ByteArray,
        profilePictureFileName: String
    ): String {
        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException("User must be logged in to upload profile picture")

        val response = remoteDataSource.uploadProfilePicture(
            userId = userId,
            customerId = customerId,
            profilePicture = profilePicture,
            profilePictureFileName = profilePictureFileName
        )
        return response.profilePictureUrl
    }

    override suspend fun getCustomerProfile(): CustomerProfile {
        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException()

        val response = remoteDataSource.getCustomerProfile(userId)
        return response.toDomain()
    }

    override suspend fun deleteCustomerAccount(customerId: String): Boolean {
        val userId = userPreferences.getUserId()
            ?: throw UnauthorizedException()
        val response = remoteDataSource.deleteCustomerAccount(userId, customerId)
        if (!response.success) {
            throw ApiException(response.message)
        }
        userPreferences.clearUserId()
        return response.success
    }
}