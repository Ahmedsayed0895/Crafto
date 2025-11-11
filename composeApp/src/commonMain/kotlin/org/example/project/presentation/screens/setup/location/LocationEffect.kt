package org.example.project.presentation.screens.setup.location

sealed class LocationEffect {
    object NavigateToNextScreen : LocationEffect()
    data class ShowError(val message: String) : LocationEffect()
}
