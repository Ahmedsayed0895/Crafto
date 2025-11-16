package org.example.project.presentation.screens.auth

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.entity.UserType
import org.example.project.domain.usecase.session.SaveUserTypeUseCase
import org.example.project.presentation.shared.base.BaseViewModel
import org.example.project.presentation.shared.base.ErrorUiState

class UserTypeSelectionViewModel(
    private val saveUserTypeUseCase: SaveUserTypeUseCase
) : BaseViewModel<UserTypeSelectionUiState, UserTypeSelectionEffect>(
    UserTypeSelectionUiState()
), UserTypeSelectionInteractionListener {

    init {
        viewModelScope.launch {
            isLoading.collect { loading ->
                updateState { it.copy(isLoading = loading) }
            }
        }
    }


    override fun onUserTypeSelected(userType: UserType) {
        updateState { it.copy(selectedType = userType) }
    }

    override fun onContinueClick() {
        val selectedType = state.value.selectedType

        if (selectedType == null) {
            updateState {
                it.copy(error = ErrorUiState("Please select a user type"))
            }
            return
        }

        tryToCall(
            call = {
                saveUserTypeUseCase(selectedType)
                selectedType
            },
            onSuccess = { type ->
                sendNewEffect(UserTypeSelectionEffect.NavigateToSetup(type))
            },
            onError = { error ->
                updateState { it.copy(error = error) }
            },
            showLoading = true
        )
    }

    fun clearError() {
        updateState { it.copy(error = null) }
    }
}