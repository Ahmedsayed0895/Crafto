package org.example.project.presentation.screens.customerRequest.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.rate
import crafto.composeapp.generated.resources.rate_done
import org.example.project.presentation.designsystem.components.BottomSheet
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RateBottomSheet(
    onClick: () -> Unit,
) {

    BottomSheet(
        showCloseIcon = true,
        onDismissRequest = { onClick() },
        headerContent = {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Rate the Craftsman",
                    style = AppTheme.textStyle.title.small,
                    color = AppTheme.craftoColors.shade.primary
                )
            }
        },
        content = {
            Column {
                Rating(
                    modifier = Modifier.fillMaxWidth()
                        .padding(bottom = 8.dp),
                    isSelected = true,
                )
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 16.dp),
                    text = "Add Rating",
                    enabled = true,
                    buttonState = ButtonState.Enable,
                    contentPadding = PaddingValues(vertical = 15.dp),
                    onClick = {
                        onClick()
                    }
                )
            }
        }
    )
}

@Composable
private fun Rating(
    modifier: Modifier = Modifier,
    isSelected: Boolean
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(5) {
            Image(
                painter = painterResource(if (isSelected) Res.drawable.rate_done else Res.drawable.rate),
                contentDescription = "rate",
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun RateBottomSheetPreview() {
    AppTheme(
        isDarkTheme = false
    ) {
        RateBottomSheet(
            onClick = {}
        )
    }
}