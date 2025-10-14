package org.example.project.data.remote.datasource

import org.example.project.data.dto.CraftsmanProfileResponseDto
import org.example.project.data.dto.CraftsmanSetupResponseDto
import org.example.project.data.dto.CraftsmanStatusResponseDto
import org.example.project.data.dto.CreateCraftsmanRequest
import org.example.project.data.dto.DeleteAccountResponseDto
import org.example.project.data.dto.IdCardUploadResponseDto
import org.example.project.data.dto.WorkPortfolioResponseDto
import org.example.project.domain.model.WorkImage

interface CraftsmanRemoteDataSource {
    suspend fun createCraftsmanProfile(
        userId: String,
        request: CreateCraftsmanRequest
    ): CraftsmanSetupResponseDto

    suspend fun uploadIdCards(
        userId: String,
        craftsmanId: String,
        idCardFront: ByteArray,
        idCardFrontFileName: String,
        idCardBack: ByteArray,
        idCardBackFileName: String
    ): IdCardUploadResponseDto

    suspend fun uploadWorkPortfolio(
        userId: String,
        craftsmanId: String,
        workImages: List<WorkImage>
    ): WorkPortfolioResponseDto

    suspend fun getCraftsmanProfile(userId: String): CraftsmanProfileResponseDto

    suspend fun getCraftsmanStatus(craftsmanId: String): CraftsmanStatusResponseDto

    suspend fun deleteCraftsmanAccount(
        userId: String,
        craftsmanId: String
    ): DeleteAccountResponseDto
}