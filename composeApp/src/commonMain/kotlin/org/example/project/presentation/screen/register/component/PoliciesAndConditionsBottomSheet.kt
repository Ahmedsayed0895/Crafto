package org.example.project.presentation.screen.register.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import crafto.composeapp.generated.resources.privacy_policy_content_1_header
import crafto.composeapp.generated.resources.privacy_policy_content_1_steps
import crafto.composeapp.generated.resources.privacy_policy_content_2_header
import crafto.composeapp.generated.resources.privacy_policy_content_2_steps
import crafto.composeapp.generated.resources.privacy_policy_content_3_description
import crafto.composeapp.generated.resources.privacy_policy_content_3_header
import crafto.composeapp.generated.resources.privacy_policy_content_3_steps
import crafto.composeapp.generated.resources.privacy_policy_content_4_description
import crafto.composeapp.generated.resources.privacy_policy_content_4_header
import crafto.composeapp.generated.resources.privacy_policy_content_4_steps
import crafto.composeapp.generated.resources.privacy_policy_content_5_description
import crafto.composeapp.generated.resources.privacy_policy_content_5_header
import crafto.composeapp.generated.resources.privacy_policy_content_6_description
import crafto.composeapp.generated.resources.privacy_policy_content_6_header
import crafto.composeapp.generated.resources.privacy_policy_content_6_steps
import crafto.composeapp.generated.resources.privacy_policy_content_7_description
import crafto.composeapp.generated.resources.privacy_policy_content_7_header
import crafto.composeapp.generated.resources.privacy_policy_content_header
import crafto.composeapp.generated.resources.privacy_policy_header
import org.example.project.presentation.designsystem.components.BottomSheet
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class PoliciesAndConditionsText(
    val header: String,
    val description: String? = null,
    val steps: String? = null
)

@Composable
fun PoliciesAndConditionsBottomSheet(
    headerText: String,
    contentHeader: String,
    content: List<PoliciesAndConditionsText>,
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
                text = headerText,
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
            Text(
                modifier = Modifier.padding(bottom = 14.dp),
                text = contentHeader,
                style = AppTheme.textStyle.body.mediumRegular,
                color = AppTheme.craftoColors.shade.primary
            )
            content.forEach { text ->
                ViewPoliciesAndConditionsText(text)
            }

            PrimaryButton(
                modifier = Modifier
                    .heightIn(min = 48.dp)
                    .padding(top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                text = stringResource(Res.string.ok_text),
                contentPadding = PaddingValues(14.dp),
                enabled = true,
                buttonState = ButtonState.Enable,
                onClick = onDismissRequest
            )
        }
    }
}

@Composable
private fun ViewPoliciesAndConditionsText(
    policiesAndConditionsText: PoliciesAndConditionsText,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = policiesAndConditionsText.header,
            style = AppTheme.textStyle.body.mediumSemiBold,
            color = AppTheme.craftoColors.shade.primary
        )
        policiesAndConditionsText.description?.let { text ->
            Text(
                text = text,
                style = AppTheme.textStyle.body.mediumRegular,
                color = AppTheme.craftoColors.shade.primary
            )
        }
        policiesAndConditionsText.steps?.let { text ->
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = text,
                style = AppTheme.textStyle.body.mediumRegular,
                color = AppTheme.craftoColors.shade.primary
            )
        }
    }
}

@Preview
@Composable
private fun PoliciesAndConditionsBottomSheetPreview() {
    AppTheme {
        val content = listOf(
            PoliciesAndConditionsText(
                header = stringResource(Res.string.privacy_policy_content_1_header),
                steps = stringResource(Res.string.privacy_policy_content_1_steps),
            ),
            PoliciesAndConditionsText(
                header = stringResource(Res.string.privacy_policy_content_2_header),
                steps = stringResource(Res.string.privacy_policy_content_2_steps),
            ),
            PoliciesAndConditionsText(
                header = stringResource(Res.string.privacy_policy_content_3_header),
                steps = stringResource(Res.string.privacy_policy_content_3_steps),
                description = stringResource(Res.string.privacy_policy_content_3_description),
            ),
            PoliciesAndConditionsText(
                header = stringResource(Res.string.privacy_policy_content_4_header),
                steps = stringResource(Res.string.privacy_policy_content_4_steps),
                description = stringResource(Res.string.privacy_policy_content_4_description),
            ),
            PoliciesAndConditionsText(
                header = stringResource(Res.string.privacy_policy_content_5_header),
                description = stringResource(Res.string.privacy_policy_content_5_description),
            ),
            PoliciesAndConditionsText(
                header = stringResource(Res.string.privacy_policy_content_6_header),
                steps = stringResource(Res.string.privacy_policy_content_6_steps),
                description = stringResource(Res.string.privacy_policy_content_6_description),
            ),
            PoliciesAndConditionsText(
                header = stringResource(Res.string.privacy_policy_content_7_header),
                description = stringResource(Res.string.privacy_policy_content_7_description),
            ),
        )
        PoliciesAndConditionsBottomSheet(
            headerText = stringResource(Res.string.privacy_policy_header),
            contentHeader = stringResource(Res.string.privacy_policy_content_header),
            content = content,
            onDismissRequest = {},
        )
    }
}