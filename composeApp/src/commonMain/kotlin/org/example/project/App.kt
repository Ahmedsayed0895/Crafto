package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import crafto.composeapp.generated.resources.Res
import crafto.composeapp.generated.resources.ic_user
import org.example.project.designSystem.components.TextField
import org.example.project.designSystem.textStyle.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            var text1 by remember { mutableStateOf("") }
            TextField(
                labelText = "Label",
                hint = "Value",
                text = text1,
                onTextChange = { text1 = it },
                enabledState = true
            )

            var text2 by remember { mutableStateOf("Mus") }
            TextField(
                labelText = "Label",
                hint = "Enter value",
                text = text2,
                onTextChange = { text2 = it },
                errorState = true,
                errorHint = "Error message",
                enabledState = true
            )

            var text3 by remember { mutableStateOf("") }
            TextField(
                labelText = "Label",
                hint = "Value",
                text = text3,
                onTextChange = { text3 = it },
                enabledState = true
            )

            var text4 by remember { mutableStateOf("") }
            TextField(
                labelText = "Label",
                hint = "Value",
                text = text4,
                onTextChange = { text4 = it },
                showAsPassword = true,
                forgotAction = { },
                enabledState = true
            )
        }
    }
}