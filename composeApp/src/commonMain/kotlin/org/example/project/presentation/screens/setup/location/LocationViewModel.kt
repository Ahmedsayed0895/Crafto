package org.example.project.presentation.screens.setup.location

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
        updateLocationDisplay()
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
    fun updateDetailLocation(text: String) {
        updateState { it.copy(detailLocation = text) }
    }
    fun selectGovernorate(governorate: Governorates) {
        updateState {
            it.copy(
                selectedGovernorate = governorate.name,
                selectedGovernorateId = governorate.id,
                selectedDistrict = "",
                showGovernorateSheet = false
            )
        }
        fetchDistricts(governorate.id)
        updateLocationDisplay()
    }

    fun selectDistrict(district: String) {
        updateState {
            it.copy(
                selectedDistrict = district,
                showDistrictSheet = false
            )
        }
        updateLocationDisplay()
    }

    private fun updateLocationDisplay() {
        updateState { currentState ->
            val parts = listOfNotNull(
                currentState.selectedGovernorate.takeIf { it.isNotBlank() },
                currentState.selectedDistrict.takeIf { it.isNotBlank() }
            )
            val displayText = parts.joinToString(", ")

            currentState.copy(locationDisplayText = displayText)
        }
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