package org.example.project.presentation.screens.onboarding.model

import org.example.project.domain.entity.OnboardingItem

data class OnboardingUiState(
    val imageRes: String,
    val title: String,
    val description: String
)

fun OnboardingItem.toUiState() : OnboardingUiState =
    OnboardingUiState(
        imageRes = imageRes,
        title = title,
        description = description
    )