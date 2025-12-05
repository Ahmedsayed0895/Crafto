package org.example.project.presentation.screens.customer_home.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.location
import crafto.composeapp.generated.resources.notifications
import org.example.project.presentation.designsystem.components.AppBar
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomerHomeAppBar(
    customerName: String,
    customerLocation: String,
    onNotificationIconClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AppBar(
        modifier = modifier,
        paddingValues = PaddingValues(start = 16.dp, end = 8.dp),
        background = AppTheme.craftoColors.background.card,
        title = "Good Morning, $customerName!",
        endIcon = painterResource(Res.drawable.notifications),
        onEndIconClick = onNotificationIconClicked,
        locationItem = {
            Row (modifier = Modifier.padding(top = 4.dp)) {
                Icon(
                    modifier = modifier.size(16.dp),
                    painter = painterResource(Res.drawable.location),
                    contentDescription = "",
                    tint = AppTheme.craftoColors.shade.secondary,
                )
                Text(
                    text = customerLocation,
                    style = AppTheme.textStyle.body.smallMedium,
                    color = AppTheme.craftoColors.shade.secondary
                )
            }
        }
    )
}

@Preview
@Composable
private fun CustomerHomeAppBarPreview(){
    AppTheme{
        CustomerHomeAppBar(
            customerName = "Mostafa",
            customerLocation = "Baghdad, Iraq",
            onNotificationIconClicked = {}
        )
    }
}