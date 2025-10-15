package org.example.project.presentation.viewmodel.base

interface BaseScreenState {
    val isLoading: Boolean
    val error: ErrorUiState?
}
