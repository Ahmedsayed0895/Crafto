package org.example.project.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateCustomerRequest(
    val personalInfo: CustomerPersonalInfoDto,
    val categories: List<String>,
    val location: CustomerLocationDto
)

@Serializable
data class CustomerSetupResponseDto(
    val customerId: String,
    val message: String)

@Serializable
data class CustomerProfileResponseDto(
    val customerId: String,
    val personalInfo: CustomerPersonalInfoDto,
    val categories: List<String>,
    val location: CustomerLocationDto,
    val profilePictureUrl: String? = null,
    val createdAt: String
)

@Serializable
data class CustomerPersonalInfoDto(
    val name: String,
    val phoneNumber: String,
)

@Serializable
data class CustomerLocationDto(
    val governorate: String,
    val district: String,
    val detailedLocation: String
)