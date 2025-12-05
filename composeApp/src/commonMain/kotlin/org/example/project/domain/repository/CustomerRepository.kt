package org.example.project.domain.repository

import org.example.project.domain.entity.CustomerLocation
import org.example.project.domain.entity.CustomerPersonalInfo
import org.example.project.domain.entity.CustomerProfile

interface CustomerRepository {
    suspend fun createCustomerProfile(
        personalInfo: CustomerPersonalInfo,
        categories: List<String>,
        location: CustomerLocation
    ): String

    suspend fun uploadProfilePicture(
        customerId: String,
        profilePicture: ByteArray,
        profilePictureFileName: String
    ): String

    suspend fun getCustomerProfile(): CustomerProfile

    suspend fun deleteCustomerAccount(customerId: String): Boolean
}