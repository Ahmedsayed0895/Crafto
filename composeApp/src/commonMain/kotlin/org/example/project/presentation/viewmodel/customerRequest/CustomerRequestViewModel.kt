package org.example.project.presentation.viewmodel.customerRequest

import org.example.project.domain.repository.CustomerRequestRepository
import org.example.project.presentation.viewmodel.base.BaseViewModel
import org.example.project.presentation.viewmodel.base.ErrorUiState
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Provided
import kotlin.uuid.ExperimentalUuidApi

@KoinViewModel
@OptIn(ExperimentalUuidApi::class)
class CustomerRequestViewModel(
    @Provided private val customerRequestRepository: CustomerRequestRepository
) : BaseViewModel<CustomerRequestScreenUiState, CustomerRequestInteractionListener>(
    CustomerRequestScreenUiState()
), CustomerRequestInteractionListener {

    init {
        loadData("68d2c71f05ff5aacfb53bf96")
    }

    private fun loadData(customerId: String){
        tryToCall(
            call = {
                updateState { it.copy(isLoading = true) }
                customerRequestRepository.getCustomersRequests(customerId)
            },
            onSuccess = {requests->
                updateState {
                    it.copy(
                        isLoading = false,
                        listOfRequests =requests.map { it.toCustomerRequestUiState() }
                    )
                }
            },
            onError = {error->
                updateState {
                    it.copy(
                        isLoading = false,
                        errorUiState = ErrorUiState(error.message)
                    )
                }
            }
        )
    }

    override fun onTabClicked(tabs: CustomerRequestScreenUiState.RequestsTab) {
        updateState {
            it.copy(
                selectedTapIndex = tabs
            )
        }
    }

    override fun onRateClicked() {
        updateState {
            it.copy(
                isBottomSheetVisible = true
            )
        }
    }

    override fun onDismissBottomSheet() {
        updateState {
            it.copy(
                isBottomSheetVisible = false
            )

        }
    }

    override fun onBottomSheetConfirm(numberOfRating: Int) {
        updateState {
            it.copy(
                numberOfRating = numberOfRating
            )
        }
    }

}