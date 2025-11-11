package org.example.project.presentation.screens.setup.location

import org.example.project.domain.entity.District
import org.example.project.domain.entity.Governorates

data class LocationUiState(
    val governorates: List<Governorates> = emptyList(),
    val districts: List<District> = emptyList(),
    val selectedGovernorate: String = "",
    val selectedGovernorateId: String = "",
    val selectedDistrict: String = "",
    val detailLocation: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val showGovernorateSheet: Boolean = false,
    val showDistrictSheet: Boolean = false,
    val locationDisplayText: String = "Governorate, District"
)