package org.example.project.presentation.viewmodel.accountSetup

import org.example.project.domain.entity.Category

data class AccountSetupState(
    val title: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val categoryState: AccountSetupCategoryState = AccountSetupCategoryState()
)

data class AccountSetupCategoryState(
    val categories: List<Category> = emptyList(),
    val isSelected: Boolean = false,
)
