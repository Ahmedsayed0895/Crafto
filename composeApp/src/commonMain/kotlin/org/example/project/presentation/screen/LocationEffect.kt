package org.example.project.presentation.screen

sealed class LocationEffect {
    object NavigateToNextScreen : LocationEffect()
    data class ShowError(val message: String) : LocationEffect()
}