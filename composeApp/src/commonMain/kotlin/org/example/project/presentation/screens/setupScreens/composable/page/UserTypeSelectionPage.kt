package org.example.project.presentation.screens.setupscreens.composable.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.selection_craftsman
import crafto.composeapp.generated.resources.selection_customer
import org.example.project.presentation.designsystem.components.SelectionCard
import org.example.project.presentation.screens.setupscreens.craftsmansetup.UserType
import org.jetbrains.compose.resources.painterResource

@Composable
fun UserTypeSelectionPage(
    selectedType: UserType?,
    onTypeSelected: (UserType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SelectionCard(
            img = painterResource(Res.drawable.selection_customer),
            title = "Customer",
            caption = "I need help with a\nservice",
            isSelected = selectedType == UserType.CUSTOMER,
            onCardClick = { onTypeSelected(UserType.CUSTOMER) },
            modifier = Modifier.weight(1f)
        )
        SelectionCard(
            img = painterResource(Res.drawable.selection_craftsman),
            title = "Craftsman",
            caption = "I offer services",
            isSelected = selectedType == UserType.CRAFTSMAN,
            onCardClick = { onTypeSelected(UserType.CRAFTSMAN) },
            modifier = Modifier.weight(1f)
        )
    }
}