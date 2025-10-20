package org.example.project.presentation.viewmodel.craftsmansetup

sealed interface CraftsmanRegistrationEffect {
    data object RegistrationComplete : CraftsmanRegistrationEffect
}