package org.example.project.presentation.screens.shared.base

data class ErrorUiState (
    val message: String = "",
    val errorType: ErrorType = ErrorType.UNKNOWN,
){
    enum class ErrorType {
        NETWORK,
        AUTHENTICATION,
        VALIDATION,
        SERVER,
        UNKNOWN
    }
}