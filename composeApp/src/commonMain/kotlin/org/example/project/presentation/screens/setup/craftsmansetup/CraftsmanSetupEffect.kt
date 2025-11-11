package org.example.project.presentation.screens.setup.craftsmansetup

sealed interface CraftsmanRegistrationEffect {
    data object RegistrationComplete : CraftsmanRegistrationEffect
}