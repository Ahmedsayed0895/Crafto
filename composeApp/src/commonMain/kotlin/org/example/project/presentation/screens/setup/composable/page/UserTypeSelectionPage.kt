package org.example.project.presentation.screens.setup.composable.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.craftsman
import crafto.composeapp.generated.resources.craftsman_description
import crafto.composeapp.generated.resources.customer
import crafto.composeapp.generated.resources.customer_description
import crafto.composeapp.generated.resources.selection_craftsman
import crafto.composeapp.generated.resources.selection_customer
import org.example.project.domain.entity.UserType
import org.example.project.presentation.designsystem.components.SelectionCard
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserTypeSelectionPage(
    selectedType: UserType?,
    onTypeSelected: (UserType) -> Unit,
    modifier: Modifier=Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SelectionCard(
            img = painterResource(Res.drawable.selection_customer),
            title = stringResource(Res.string.customer),
            caption = stringResource(Res.string.customer_description),
            isSelected = selectedType == UserType.CUSTOMER,
            onCardClick = { onTypeSelected(UserType.CUSTOMER) },
            modifier = Modifier.weight(1f)
        )
        SelectionCard(
            img = painterResource(Res.drawable.selection_craftsman),
            title = stringResource(Res.string.craftsman),
            caption = stringResource(Res.string.craftsman_description),
            isSelected = selectedType == UserType.CRAFTSMAN,
            onCardClick = { onTypeSelected(UserType.CRAFTSMAN) },
            modifier = Modifier.weight(1f)
        )
    }
}