package org.example.project.presentation.viewmodel.customerRequest

import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.cancel
import crafto.composeapp.generated.resources.complete
import crafto.composeapp.generated.resources.ongoing
import org.jetbrains.compose.resources.StringResource

data class CustomerRequestUiState(
    val selectedTapIndex: Tabs = Tabs.ONGOING,
    val numberOfRating: Int? = null,
)

enum class Tabs(tabTitle: StringResource) {
    ONGOING(tabTitle = Res.string.ongoing),
    COMPLETE(tabTitle = Res.string.complete),
    CANCEL(tabTitle = Res.string.cancel)
}