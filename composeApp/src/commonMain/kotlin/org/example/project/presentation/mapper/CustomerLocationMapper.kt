package org.example.project.presentation.mapper

import org.example.project.domain.entity.CustomerLocation
import org.example.project.domain.entity.District
import org.example.project.domain.entity.Governorates
import org.example.project.presentation.model.DistrictUiModel
import org.example.project.presentation.model.GovernoratesUiModel
import org.example.project.presentation.model.LocationUiModel

fun LocationUiModel.toDomain(): CustomerLocation {
    return CustomerLocation(
        governorate = governorate,
        district = district,
        detailedLocation = detailedLocation
    )
}

fun CustomerLocation.toUi(): LocationUiModel {
    return LocationUiModel(
        governorate = governorate,
        district = district,
        detailedLocation = detailedLocation
    )
}


fun DistrictUiModel.toDomain(): District {
    return District(
        id = id,
        name = name,
        governorateId = governorateId
    )
}

fun District.toUi(): DistrictUiModel {
    return DistrictUiModel(
        id = id,
        name = name,
        governorateId = governorateId
    )
}

fun GovernoratesUiModel.toDomain(): Governorates {
    return Governorates(
        id = id,
        name = name
    )
}

fun Governorates.toUi(): GovernoratesUiModel {
    return GovernoratesUiModel(
        id = id,
        name = name
    )
}