package org.example.project.presentation.screen.register.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.check_read
import crafto.composeapp.generated.resources.register_error
import org.example.project.designSystem.components.BottomSheet
import org.example.project.designSystem.components.ButtonState
import org.example.project.designSystem.components.PrimaryButton
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorBottomSheet(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
){
    BottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
    ){
        Column (modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.size(120.dp),
                contentDescription = "register error",
                painter = painterResource(Res.drawable.register_error),
            )
            Text(
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                text = "Oops, Something Broke",
                textAlign = TextAlign.Center,
                style = AppTheme.textStyle.title.small,
                color = AppTheme.craftoColors.shade.primary
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Our team is working on a fix. Please try again later.",
                style = AppTheme.textStyle.body.medium,
                color = AppTheme.craftoColors.shade.secondary
            )
            PrimaryButton (
                modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
                text = "Ok",
                enabled = true,
                buttonState = ButtonState.Enable,
                onClick = onDismissRequest
            )
        }
    }
}

@Preview
@Composable
private fun ErrorBottomSheetPreview(){
    ErrorBottomSheet(
        onDismissRequest = {},
    )
}