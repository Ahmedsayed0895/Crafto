package org.example.project.presentation.screens.shared.base

interface BaseScreenState {
    val isLoading: Boolean
    val error: ErrorUiState?
}