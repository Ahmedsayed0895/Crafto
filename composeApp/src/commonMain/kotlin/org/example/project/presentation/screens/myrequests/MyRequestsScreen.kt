package org.example.project.presentation.screens.myrequests

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.presentation.designsystem.textstyle.AppTheme

@Composable
fun MyRequestsScreen(

) {
    Box(
        modifier = Modifier.fillMaxSize().background(AppTheme.craftoColors.brand.primary)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("My Requests Screen", style = AppTheme.textStyle.title.large,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}