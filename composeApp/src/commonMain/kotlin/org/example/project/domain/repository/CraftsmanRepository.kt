package org.example.project.domain.repository

import org.example.project.domain.entity.CraftsmanProfile
import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.entity.CraftsmanPersonalInfo
import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.model.WorkImage

interface CraftsmanRepository {
    suspend fun createCraftsmanProfile(
        craftsmanPersonalInfo: CraftsmanPersonalInfo,
        categories: List<String>
    ): String

    suspend fun uploadIdCards(
        craftsmanId: String,
        idCardFront: ByteArray,
        idCardFrontFileName: String,
        idCardBack: ByteArray,
        idCardBackFileName: String
    ): VerificationDocuments

    suspend fun uploadProfilePicture(
        craftsmanId: String,
        profilePicture: ByteArray,
        profilePictureFileName: String
    ): String

    suspend fun uploadWorkPortfolio(
        craftsmanId: String,
        workImages: List<WorkImage>
    ): List<String>

    suspend fun getCraftsmanProfile(): CraftsmanProfile

    suspend fun getCraftsmanStatus(craftsmanId: String): CraftsmanStatus

    suspend fun deleteCraftsmanAccount(craftsmanId: String): Boolean
}