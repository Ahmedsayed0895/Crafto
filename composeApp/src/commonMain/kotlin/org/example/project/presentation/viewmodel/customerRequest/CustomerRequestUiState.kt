package org.example.project.presentation.viewmodel.customerRequest

import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.cancel
import crafto.composeapp.generated.resources.complete
import crafto.composeapp.generated.resources.ongoing
import org.example.project.domain.entity.CustomerRequest
import org.jetbrains.compose.resources.StringResource

data class CustomerRequestUiState(
    val selectedTapIndex: Tabs = Tabs.ONGOING,
    val numberOfRating: Int? = null,
    val listOfRequests: List<CustomerRequest>? =emptyList()
)

enum class Tabs(val tabTitle: StringResource) {
    ONGOING(tabTitle = Res.string.ongoing),
    COMPLETE(tabTitle = Res.string.complete),
    CANCEL(tabTitle = Res.string.cancel)
}

//data class CustomerRequest()