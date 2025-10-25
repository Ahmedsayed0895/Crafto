package org.example.project.presentation.screens.customerRequest

import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.cancel
import crafto.composeapp.generated.resources.complete
import crafto.composeapp.generated.resources.ongoing
import org.example.project.domain.entity.Category
import org.example.project.domain.entity.CraftsmanOffer
import org.example.project.domain.entity.CustomerRequest
import org.example.project.presentation.shared.base.ErrorUiState
import org.jetbrains.compose.resources.StringResource

data class CustomerRequestScreenUiState(
    val selectedTapIndex: RequestsTab = RequestsTab.ONGOING,
    val numberOfRating: Int? = null,
    val isBottomSheetVisible: Boolean = false,
    val isLoading: Boolean = false,
    val listOfRequests: List<CustomerRequestUiState> = emptyList(),
    val errorUiState: ErrorUiState? = null
) {
    enum class RequestsTab(val tabTitle: StringResource) {
        ONGOING(tabTitle = Res.string.ongoing),
        COMPLETED(tabTitle = Res.string.complete),
        CANCELED(tabTitle = Res.string.cancel)
    }

    data class CustomerRequestUiState(
        val requestStatus: RequestsTab,
        val issueTitle: String,
        val issueDescription: String,
        val issueCategory: CategoryUi,
        val offers: List<CraftsmanOffer>? = null ///////////////////////////////
    )

    data class CategoryUi(
        val icon: String,
        val title: String,
        val color: String
    )

}

fun Category.toCategoryUiState(): CustomerRequestScreenUiState.CategoryUi {
    return CustomerRequestScreenUiState.CategoryUi(
        icon = iconUrl,
        title = title,
        color = color
    )
}

fun CustomerRequest.toCustomerRequestUiState(): CustomerRequestScreenUiState.CustomerRequestUiState {
    val requestStatus = when (status) {
        CustomerRequest.CustomerIssueStatus.RECEIVING_OFFERS -> CustomerRequestScreenUiState.RequestsTab.ONGOING
        CustomerRequest.CustomerIssueStatus.CRAFTSMAN_SELECTED -> CustomerRequestScreenUiState.RequestsTab.ONGOING
        CustomerRequest.CustomerIssueStatus.SUBMITTED -> CustomerRequestScreenUiState.RequestsTab.ONGOING
        CustomerRequest.CustomerIssueStatus.IN_PROGRESS -> CustomerRequestScreenUiState.RequestsTab.ONGOING
        CustomerRequest.CustomerIssueStatus.DONE -> CustomerRequestScreenUiState.RequestsTab.COMPLETED
        CustomerRequest.CustomerIssueStatus.CANCELED -> CustomerRequestScreenUiState.RequestsTab.CANCELED
    }
    return CustomerRequestScreenUiState.CustomerRequestUiState(
        requestStatus = requestStatus,
        issueTitle = title,
        issueDescription = description,
        issueCategory = category.toCategoryUiState(),
        offers = offers
    )
}


