package org.example.project.presentation.mapper

import org.example.project.domain.entity.CustomerPersonalInfo
import org.example.project.presentation.model.CustomerPersonalInfoUiModel

fun CustomerPersonalInfoUiModel.toDomain(): CustomerPersonalInfo {
    return CustomerPersonalInfo(
        name = name,
        phoneNumber = phoneNumber,
    )
}