package org.example.project.presentation.viewmodel.customerRequest

interface CustomerRequestInteractionListener {

    fun onTabClicked(tabs: Tabs)
    fun onRateClicked()
    fun onDismissBottomSheet()
}