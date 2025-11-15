package org.example.project.presentation.screens.auth

import org.example.project.domain.entity.UserType

interface UserTypeSelectionInteractionListener {
    fun onUserTypeSelected(userType: UserType)
    fun onContinueClick()
}