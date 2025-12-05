package org.example.project.data.remote.datasource

import org.example.project.data.remote.dto.CreateCustomerRequest
import org.example.project.data.remote.dto.CustomerProfileResponseDto
import org.example.project.data.remote.dto.CustomerSetupResponseDto
import org.example.project.data.remote.dto.DeleteAccountResponseDto
import org.example.project.data.remote.dto.ProfilePictureUploadResponseDto

interface CustomerRemoteDataSource {
    suspend fun createCustomerProfile(
        userId: String,
        request: CreateCustomerRequest
    ): CustomerSetupResponseDto

    suspend fun uploadProfilePicture(
        userId: String,
        customerId: String,
        profilePicture: ByteArray,
        profilePictureFileName: String
    ): ProfilePictureUploadResponseDto

    suspend fun getCustomerProfile(userId: String): CustomerProfileResponseDto

    suspend fun deleteCustomerAccount(
        userId: String,
        customerId: String
    ): DeleteAccountResponseDto
}