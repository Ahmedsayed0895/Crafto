package org.example.project.presentation.viewmodel.accountSetup

import org.example.project.domain.entity.Category

data class AccountSetupState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSelected: Boolean = false,
    val categories: List<Category> = emptyList(),
    val isCustomer: Boolean = true,
)
