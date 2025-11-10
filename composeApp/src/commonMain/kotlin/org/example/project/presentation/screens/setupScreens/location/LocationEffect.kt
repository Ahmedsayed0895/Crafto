package org.example.project.presentation.screens.setupScreens.location

sealed class LocationEffect {
    object NavigateToNextScreen : LocationEffect()
    data class ShowError(val message: String) : LocationEffect()
}
