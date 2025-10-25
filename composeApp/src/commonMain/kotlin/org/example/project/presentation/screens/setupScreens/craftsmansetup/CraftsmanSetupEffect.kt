package org.example.project.presentation.screens.setupscreens.craftsmansetup

sealed interface CraftsmanRegistrationEffect {
    data object RegistrationComplete : CraftsmanRegistrationEffect
}