package org.example.project.data.mapper

import org.example.project.data.remote.dto.CustomerLocationDto
import org.example.project.data.remote.dto.CustomerPersonalInfoDto
import org.example.project.data.remote.dto.CustomerProfileResponseDto
import org.example.project.domain.entity.CustomerLocation
import org.example.project.domain.entity.CustomerPersonalInfo
import org.example.project.domain.entity.CustomerProfile
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun CustomerProfileResponseDto.toDomain(): CustomerProfile {
    return CustomerProfile(
        customerId = customerId,
        personalInfo = personalInfo.toDomain(),
        categories = categories,
        location = location.toDomain(),
        profilePictureUrl = profilePictureUrl,
        createdAt = Instant.parse(createdAt)
    )
}

fun CustomerPersonalInfo.toDto(): CustomerPersonalInfoDto {
    return CustomerPersonalInfoDto(
        name = name,
        phoneNumber = phoneNumber
    )
}

fun CustomerPersonalInfoDto.toDomain(): CustomerPersonalInfo {
    return CustomerPersonalInfo(
        name = name,
        phoneNumber = phoneNumber
    )
}

fun CustomerLocation.toDto(): CustomerLocationDto{
    return CustomerLocationDto(
        governorate = governorate,
        district = district,
        detailedLocation = detailedLocation
    )
}

fun CustomerLocationDto.toDomain(): CustomerLocation {
    return CustomerLocation(
        governorate = governorate,
        district = district,
        detailedLocation = detailedLocation
    )
}