package org.example.project.presentation.viewmodel.mapper

import org.example.project.domain.exception.ForbiddenException
import org.example.project.domain.exception.NetworkException
import org.example.project.domain.exception.UnauthorizedException
import org.example.project.domain.exception.ValidationException
import org.example.project.presentation.viewmodel.base.ErrorUiState

fun Throwable.toErrorUiState(): ErrorUiState {
    return when (this) {
        is NetworkException -> ErrorUiState(
            message = message ?: "Please check your internet connection",
            errorType = ErrorUiState.ErrorType.NETWORK,
        )
        is UnauthorizedException -> ErrorUiState(
            message = message ?: "Please login to continue",
            errorType = ErrorUiState.ErrorType.AUTHENTICATION,
        )
        is ValidationException -> ErrorUiState(
            message = message ?: "Please check your input",
            errorType = ErrorUiState.ErrorType.VALIDATION,
        )
        is ForbiddenException -> ErrorUiState(
            message = message ?: "You don't have permission",
            errorType = ErrorUiState.ErrorType.AUTHENTICATION,
        )
        else -> ErrorUiState(
            message = message ?: "Something went wrong",
            errorType = ErrorUiState.ErrorType.UNKNOWN,
        )
    }
}