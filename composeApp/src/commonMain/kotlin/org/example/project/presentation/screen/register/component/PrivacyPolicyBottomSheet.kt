package org.example.project.presentation.screen.register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.ok_text
import crafto.composeapp.generated.resources.privacy_policy_content
import crafto.composeapp.generated.resources.privacy_policy_header
import org.example.project.designSystem.components.BottomSheet
import org.example.project.designSystem.components.ButtonState
import org.example.project.designSystem.components.PrimaryButton
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun PrivacyPolicyBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        showCloseIcon = true,
        headerContent = {
            Text(
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                text = stringResource(Res.string.privacy_policy_header),
                style = AppTheme.textStyle.title.small,
                color = AppTheme.craftoColors.shade.primary
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            FormattedTextContent(stringResource(Res.string.privacy_policy_content))
            PrimaryButton(
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .padding(top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                text = stringResource(Res.string.ok_text),
                enabled = true,
                buttonState = ButtonState.Enable,
                onClick = onDismissRequest
            )
        }
    }
}

@Preview
@Composable
private fun PrivacyPolicyBottomSheetPreview() {
    AppTheme {
        PrivacyPolicyBottomSheet(
            onDismissRequest = {},
        )
    }
}