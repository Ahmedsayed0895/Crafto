package org.example.project.presentation.screens.customerRequest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.notifications
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.example.project.presentation.screens.customerRequest.component.NoServiceRequestPlaceholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomerRequestScreen(){

    CustomerRequestContent()
}


@Composable
private fun CustomerRequestContent(
    modifier: Modifier= Modifier
){

    Column(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
                .background(AppTheme.craftoColors.background.card)
                .statusBarsPadding()
                .padding(vertical = 18.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "My Requests",
                style = AppTheme.textStyle.title.small,
                color = AppTheme.craftoColors.shade.primary
            )
            Icon(
                painter = painterResource(Res.drawable.notifications),
                contentDescription = "notification",
                tint = AppTheme.craftoColors.shade.primary,
            )
        }

        NoServiceRequestPlaceholder(
            modifier = modifier.fillMaxSize()
                .padding(horizontal = 40.dp)
        )

//        TabRow(
//            selectedTabIndex = TODO(),
//            modifier = TODO(),
//            containerColor = TODO(),
//            contentColor = TODO(),
//            indicator = TODO(),
//            divider = TODO(),
//            tabs = TODO()
//        )

    }

}

@Preview
@Composable
private fun CustomerRequestContentPreview(){
    AppTheme{
        CustomerRequestContent()
    }
}