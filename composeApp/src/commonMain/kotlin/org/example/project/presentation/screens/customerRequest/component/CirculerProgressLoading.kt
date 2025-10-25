package org.example.project.presentation.screens.customerRequest.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.presentation.designsystem.textstyle.AppTheme

@Composable
fun CircularProgressLoading(modifier: Modifier= Modifier){
    Column(
        modifier = modifier.fillMaxSize().background(color = AppTheme.craftoColors.background.screen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = AppTheme.craftoColors.additional.primaryBlue,)
    }
}