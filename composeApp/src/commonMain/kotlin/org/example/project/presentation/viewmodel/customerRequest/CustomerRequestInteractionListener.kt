package org.example.project.presentation.viewmodel.customerRequest

interface CustomerRequestInteractionListener {

    fun onTabClicked(tabs: CustomerRequestScreenUiState.RequestsTab)
    fun onRateClicked()
    fun onDismissBottomSheet()
    fun onBottomSheetConfirm(numberOfRating: Int)
}