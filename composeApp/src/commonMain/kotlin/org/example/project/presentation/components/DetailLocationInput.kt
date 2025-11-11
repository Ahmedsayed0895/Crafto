package org.example.project.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.project.presentation.designsystem.components.TextField
import org.jetbrains.compose.resources.stringResource
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.enter_detailed_location

@Composable
fun DetailLocationInput(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        hint = stringResource(Res.string.enter_detailed_location),
        text = text,
        onTextChange = onTextChange,
        modifier = modifier.fillMaxWidth()
    )
}