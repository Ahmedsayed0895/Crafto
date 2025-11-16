package org.example.project.presentation.screens.auth

import org.example.project.domain.entity.UserType

sealed interface UserTypeSelectionEffect {
    data class NavigateToSetup(val userType: UserType) : UserTypeSelectionEffect
}