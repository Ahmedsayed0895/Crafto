package org.example.project.domain.repository

import org.example.project.domain.entity.Craftsman
import org.example.project.domain.entity.CraftsmanStatus
import org.example.project.domain.entity.PersonalInfo
import org.example.project.domain.entity.VerificationDocuments
import org.example.project.domain.model.WorkImage

interface CraftsmanRepository {
    suspend fun createCraftsmanProfile(
        personalInfo: PersonalInfo,
        categories: List<String>
    ): String

    suspend fun uploadIdCards(
        craftsmanId: String,
        idCardFront: ByteArray,
        idCardFrontFileName: String,
        idCardBack: ByteArray,
        idCardBackFileName: String
    ): VerificationDocuments

    suspend fun uploadWorkPortfolio(
        craftsmanId: String,
        workImages: List<WorkImage>
    ): List<String>

    suspend fun getCraftsmanProfile(): Craftsman

    suspend fun getCraftsmanStatus(craftsmanId: String): CraftsmanStatus

    suspend fun deleteCraftsmanAccount(craftsmanId: String)
}