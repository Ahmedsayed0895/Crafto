package org.example.project.presentation.shared.base

interface BaseScreenState {
    val isLoading: Boolean
    val error: ErrorUiState?
}