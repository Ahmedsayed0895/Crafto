package org.example.project.presentation.viewmodel.accountSetup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.presentation.viewmodel.base.BaseViewModel

class AccountSetupViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : BaseViewModel<AccountSetupState, AccountSetupEffect>(AccountSetupState()),
    AccountSetupInterActionListener {

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            val categories = getCategoriesUseCase()
            updateState { it.copy(categories = categories) }
        }
    }


    override fun onCategorySelected(id: Int) {
        updateState {
            val categories = it.categories.map { category ->
                if (category.id == id) {
                    category.copy(isSelected = !category.isSelected)
                } else {
                    category
                }
            }
            it.copy(categories = categories)
        }

    }
}