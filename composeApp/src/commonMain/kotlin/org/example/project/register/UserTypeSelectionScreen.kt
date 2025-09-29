package org.example.project.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
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
import crafto.composeapp.generated.resources.craftsman
import crafto.composeapp.generated.resources.customer
import crafto.composeapp.generated.resources.how_would_you_like_to_use_san3a
import crafto.composeapp.generated.resources.i_need_help_with_a_service
import crafto.composeapp.generated.resources.i_offer_services
import crafto.composeapp.generated.resources.next
import crafto.composeapp.generated.resources.you_can_switch_roles_anytime
import org.example.project.designSystem.components.ButtonState
import org.example.project.designSystem.components.PrimaryButton
import org.example.project.designSystem.components.ProgressIndicator
import org.example.project.designSystem.components.SelectionCard
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun UserTypeSelectionScreen(
    onNextClick: () -> Unit
) {
    var selectedRole by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .background(AppTheme.craftoColors.background.screen)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        ProgressIndicator(
            currentPage = 1,
            totalPage = 4,
            modifier = Modifier
                .fillMaxWidth(0.75f)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(Res.string.how_would_you_like_to_use_san3a),
                style = AppTheme.textStyle.display,
                color = AppTheme.craftoColors.shade.primary
            )

            Text(
                text = stringResource(Res.string.you_can_switch_roles_anytime),
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
                title = stringResource(Res.string.customer),
                caption = stringResource(Res.string.i_need_help_with_a_service),
                isSelected = selectedRole == "Customer",
                onCardClick = { selectedRole = "Customer" },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .fillMaxHeight()
            )

            SelectionCard(
                img = painterResource(Res.drawable.carftsman),
                title = stringResource(Res.string.craftsman),
                caption = stringResource(Res.string.i_offer_services),
                isSelected = selectedRole == "Craftsman",
                onCardClick = { selectedRole = "Craftsman" },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .fillMaxHeight()
            )
        }

        PrimaryButton(
            text = stringResource(Res.string.next),
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
private fun UserTypeSelectionScreenPreview() {
    AppTheme(isDarkTheme = false) {
        UserTypeSelectionScreen(onNextClick = {})
    }
}