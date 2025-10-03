package org.example.project.presentation.viewmodel.accountSetup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.presentation.viewmodel.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided

@KoinViewModel
class AccountSetupViewModel(
    @Provided private val getCategoriesUseCase : GetCategoriesUseCase
) : BaseViewModel<AccountSetupState, AccountSetupEffect>(AccountSetupState()),
    AccountSetupInterActionListener {

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            val categories = getCategoriesUseCase.invoke()
            updateState {
                it.copy(
                    categoryState = it.categoryState.copy(categories = categories)
                )
            }
        }
    }

    override fun onCategorySelected(id: Int) {
        updateState {
            val categories = it.categoryState.categories.map { category ->
                if (category.id == id) {
                    category.copy(isSelected = !category.isSelected)
                } else {
                    category
                }
            }
            it.copy(
                categoryState = it.categoryState.copy(categories = categories)
            )
        }
    }
}