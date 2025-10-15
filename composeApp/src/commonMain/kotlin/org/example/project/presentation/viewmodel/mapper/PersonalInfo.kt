package org.example.project.presentation.viewmodel.mapper

import org.example.project.domain.entity.PersonalInfo
import org.example.project.presentation.model.PersonalInfoUiModel

fun PersonalInfo.toUiModel(): PersonalInfoUiModel {
    return PersonalInfoUiModel(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        address = address
    )
}