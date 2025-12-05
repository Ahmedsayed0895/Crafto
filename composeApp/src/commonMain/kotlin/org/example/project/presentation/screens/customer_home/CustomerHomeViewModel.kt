package org.example.project.presentation.screens.customer_home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.presentation.shared.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CustomerHomeViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
): BaseViewModel<CustomerHomeScreenState, CustomerHomeScreenEffect>(
    initialState = CustomerHomeScreenState()
), CustomerHomeInteractionListener {

    init {
        observeOnLoading()
        fetchAllCategories()
    }

    override fun onSearchClicked() {
        TODO("Not yet implemented")
    }

    override fun onRequestCategoryClicked() {
        TODO("Not yet implemented")
    }

    private fun fetchAllCategories() {
        tryToCall(
            call = { getCategoriesUseCase() },
            onSuccess = { categories ->
                val categoryUiList = categories.map { it.toUi() }
                updateState { it.copy(allCategories = categoryUiList) }
            },
            onError = { error ->
                updateState { it.copy(isError = error) }
            },
            showLoading = true
        )
    }

    private fun observeOnLoading(){
        viewModelScope.launch {
            isLoading.collect { loading ->
                updateState { it.copy(isLoading = loading) }
            }
        }
    }
}