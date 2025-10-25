package org.example.project.presentation.screens.customerRequest.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
    onDismiss: () -> Unit,
    updateRate: Int? = null,
    onClick: (numberOfRate: Int) -> Unit,
) {
    var selectedRate by remember { mutableStateOf(updateRate ?: 0) }

    BottomSheet(
        showCloseIcon = true,
        onDismissRequest = { onDismiss() },
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Rating(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    currentRate = selectedRate,
                    onClick = { newRate ->
                        selectedRate = newRate
                    }
                )

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 16.dp),
                    text = "Add Rating",
                    enabled = selectedRate > 0,
                    buttonState = if (selectedRate > 0) ButtonState.Enable else ButtonState.DISABLED,
                    contentPadding = PaddingValues(vertical = 15.dp),
                    onClick = {
                        onClick(selectedRate)
                        onDismiss()
                    }
                )
            }
        }
    )
}

@Composable
private fun Rating(
    modifier: Modifier = Modifier,
    currentRate: Int,
    onClick: (rateNumber: Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(5) { index ->
            val starNumber = index + 1
            val isSelected = starNumber <= currentRate
            Image(
                painter = painterResource(
                    if (isSelected) Res.drawable.rate_done else Res.drawable.rate
                ),
                contentDescription = "Rate star",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        onClick(starNumber)
                    }
            )
        }
    }
}

@Preview
@Composable
private fun RateBottomSheetPreview() {
    AppTheme(isDarkTheme = false) {
        RateBottomSheet(
            onClick = {},
            onDismiss = {},
            updateRate = 3,
        )
    }
}
