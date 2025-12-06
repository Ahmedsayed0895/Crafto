package org.example.project.presentation.screens.setup.customersetup

sealed interface CustomerRegistrationEffect {
    data object RegistrationComplete : CustomerRegistrationEffect
}