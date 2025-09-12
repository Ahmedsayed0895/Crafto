package org.example.project.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.carftsman
import crafto.composeapp.generated.resources.customer
import org.example.project.designSystem.components.ButtonState
import org.example.project.designSystem.components.PrimaryButton
import org.example.project.designSystem.components.ProgressIndicator
import org.example.project.designSystem.components.SelectionCard
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun UserTypeSelectionScreen(
    onNextClick: () -> Unit
) {
    var selectedRole by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.craftoColors.background.screen)
            .padding(horizontal = 16.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        ProgressIndicator(
            currentPage = 1,
            totalPage = 5,
            modifier = Modifier
                .fillMaxWidth(0.75f)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "How would you like to use San3a?",
                style = AppTheme.textStyle.display,
                color = AppTheme.craftoColors.shade.primary
            )

            Text(
                text = "You can switch roles anytime from your profile.",
                style = AppTheme.textStyle.body.largeRegular,
                color = AppTheme.craftoColors.shade.secondary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            SelectionCard(
                img = painterResource(Res.drawable.customer),
                title = "Customer",
                caption = "I need help with a service",
                isSelected = selectedRole == "Customer",
                onCardClick = { selectedRole = "Customer" },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .fillMaxHeight()
            )

            SelectionCard(
                img = painterResource(Res.drawable.carftsman),
                title = "Craftsman",
                caption = "I offer services",
                isSelected = selectedRole == "Craftsman",
                onCardClick = { selectedRole = "Craftsman" },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .fillMaxHeight()
            )
        }

        PrimaryButton(
            text = "Next",
            enabled = true,
            onClick = onNextClick,
            buttonState = ButtonState.Enable,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp)
        )
    }
}

@Preview
@Composable
private fun RoleSelectionScreenPreview() {
    AppTheme(isDarkTheme = false) {

        UserTypeSelectionScreen(onNextClick = {})


    }
}