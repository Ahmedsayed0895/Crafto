package org.example.project.presentation.screens.onboarding

import org.example.project.presentation.screens.onboarding.model.OnboardingUiState
import org.example.project.presentation.viewmodel.base.ErrorUiState

data class OnboardingScreenState(
    val onboardingData: List<OnboardingUiState> = emptyList(),
    val loading: Boolean = false,
    val errorMessage : ErrorUiState = ErrorUiState("")
)