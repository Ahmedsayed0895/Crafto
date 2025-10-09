package org.example.project.presentation.viewmodel.customerRequest

import org.example.project.presentation.viewmodel.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CustomerRequestViewModel (

): BaseViewModel<CustomerRequestUiState, CustomerRequestInteractionListener>(
        CustomerRequestUiState()
), CustomerRequestInteractionListener {
        override fun onTabClicked(tabs: Tabs) {
        }

        override fun onRateClicked() {
        }

        override fun onDismissBottomSheet() {
        }


}