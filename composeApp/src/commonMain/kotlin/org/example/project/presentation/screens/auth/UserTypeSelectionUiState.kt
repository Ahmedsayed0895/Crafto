package org.example.project.presentation.screens.auth

import org.example.project.domain.entity.UserType
import org.example.project.presentation.shared.base.BaseScreenState
import org.example.project.presentation.shared.base.ErrorUiState

data class UserTypeSelectionUiState(
    override val isLoading: Boolean = false,
    override val error: ErrorUiState? = null,
    val selectedType: UserType? = null
) : BaseScreenState
