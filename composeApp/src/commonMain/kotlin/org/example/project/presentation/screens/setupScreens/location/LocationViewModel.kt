package org.example.project.presentation.screens.setupScreens.location

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.example.project.domain.entity.Governorates
import org.example.project.domain.repository.LocationRepository
import org.example.project.presentation.shared.base.BaseViewModel

class LocationViewModel(
    private val repository: LocationRepository
) : BaseViewModel<LocationUiState, LocationEffect>(initialState = LocationUiState()) {

    init {
        fetchGovernorates()
    }

    private fun fetchGovernorates() {
        tryToCall(
            call = { repository.getAllGovernorates() },
            onSuccess = { governorates ->
                updateState { it.copy(governorates = governorates, isLoading = false, error = null) }
            },
            onError = { error ->
                updateState { it.copy(isLoading = false, error = error.message) }
                sendNewEffect(LocationEffect.ShowError(error.message))
            },
            dispatcher = Dispatchers.IO
        )
    }

    fun fetchDistricts(governorateId: String) {
        tryToCall(
            call = { repository.getDistrictsByGovernorateId(governorateId) },
            onSuccess = { districts ->
                updateState {
                    it.copy(
                        districts = districts,
                        isLoading = false,
                        showDistrictSheet = districts.isNotEmpty(),
                        error = null
                    )
                }
            },
            onError = { error ->
                updateState { it.copy(isLoading = false, error = error.message) }
                sendNewEffect(LocationEffect.ShowError(error.message))
            },
            dispatcher = Dispatchers.IO
        )
    }

    fun selectGovernorate(governorate: Governorates) {
        updateState {
            it.copy(
                selectedGovernorate = governorate.name,
                selectedGovernorateId = governorate.id, // Store governorate ID
                selectedDistrict = "",
                showGovernorateSheet = false
            )
        }
        fetchDistricts(governorate.id)
    }

    fun selectDistrict(district: String) {
        updateState {
            it.copy(
                selectedDistrict = district,
                showDistrictSheet = false
            )
        }
    }

    fun updateDetailLocation(text: String) {
        updateState { it.copy(detailLocation = text) }
    }

    fun openGovernorateSheet() {
        updateState { it.copy(showGovernorateSheet = true) }
    }

    fun closeGovernorateSheet() {
        updateState { it.copy(showGovernorateSheet = false) }
    }

    fun closeDistrictSheet() {
        updateState { it.copy(showDistrictSheet = false) }
    }

    fun onNextClick() {
        sendNewEffect(LocationEffect.NavigateToNextScreen)
    }
}