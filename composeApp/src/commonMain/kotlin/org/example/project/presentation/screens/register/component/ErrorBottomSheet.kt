package org.example.project.presentation.screens.register.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.authentication_error
import crafto.composeapp.generated.resources.error_bottom_sheet_content
import crafto.composeapp.generated.resources.error_bottom_sheet_header
import crafto.composeapp.generated.resources.ok_text
import org.example.project.presentation.designsystem.components.BottomSheet
import org.example.project.presentation.designsystem.components.ButtonState
import org.example.project.presentation.designsystem.components.PrimaryButton
import org.example.project.presentation.designsystem.textstyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = "register error",
                painter = painterResource(Res.drawable.authentication_error),
            )
            Text(
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                text = stringResource(Res.string.error_bottom_sheet_header),
                textAlign = TextAlign.Center,
                style = AppTheme.textStyle.title.small,
                color = AppTheme.craftoColors.shade.primary
            )
            Text(
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                text = stringResource(Res.string.error_bottom_sheet_content),
                textAlign = TextAlign.Center,
                style = AppTheme.textStyle.body.medium,
                color = AppTheme.craftoColors.shade.secondary
            )
            PrimaryButton(
                modifier = Modifier.heightIn(min = 48.dp).padding(vertical = 24.dp).fillMaxWidth(),
                text = stringResource(Res.string.ok_text),
                enabled = true,
                buttonState = ButtonState.Enable,
                contentPadding = PaddingValues(14.dp),
                onClick = onDismissRequest
            )
        }
    }
}

@Preview
@Composable
private fun ErrorBottomSheetPreview() {
    AppTheme {
        ErrorBottomSheet(
            onDismissRequest = {},
        )
    }
}