package org.example.project.presentation.screens.customer_home

import org.example.project.presentation.shared.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CustomerHomeViewModel(): BaseViewModel<CustomerHomeScreenState, CustomerHomeScreenEffect>(
    initialState = CustomerHomeScreenState()
), CustomerHomeInteractionListener {

    override fun onSearchClicked() {
        TODO("Not yet implemented")
    }

    override fun onRequestCategoryClicked() {
        TODO("Not yet implemented")
    }
}