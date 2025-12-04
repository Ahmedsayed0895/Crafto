package org.example.project.presentation.screens.customer_home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.example.project.presentation.designsystem.components.AppBar
import org.example.project.presentation.screens.onboarding.OnboardingViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CustomerHomeScreen(
    viewModel: CustomerHomeViewModel = koinViewModel()
){
    Scaffold(
        topBar = {
            AppBar {  }
        }
    ) {

    }
}