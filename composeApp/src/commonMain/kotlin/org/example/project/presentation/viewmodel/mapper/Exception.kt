package org.example.project.presentation.viewmodel.mapper

import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import org.example.project.domain.exception.ForbiddenException
import org.example.project.domain.exception.NetworkException
import org.example.project.domain.exception.UnauthorizedException
import org.example.project.domain.exception.ValidationException
import org.example.project.presentation.viewmodel.base.ErrorUiState
import org.example.project.util.AppLogger

fun Throwable.toErrorUiState(): ErrorUiState {
    return when (this) {
        is NetworkException,
        is SocketTimeoutException,
        is HttpRequestTimeoutException -> ErrorUiState(
            message = "Please check your internet connection and try again.",
            errorType = ErrorUiState.ErrorType.NETWORK
        )

        is UnauthorizedException -> ErrorUiState(
            message = "Please login to continue.",
            errorType = ErrorUiState.ErrorType.AUTHENTICATION
        )

        is ValidationException -> ErrorUiState(
            message = message ?: "Please check your input.",
            errorType = ErrorUiState.ErrorType.VALIDATION
        )

        is ForbiddenException -> ErrorUiState(
            message = "You don't have permission to perform this action.",
            errorType = ErrorUiState.ErrorType.AUTHENTICATION
        )

        else -> {
            AppLogger.e("ThrowableEX",this.message?:"Unknown error")
            println("⚠️ Unexpected error: ${this::class.simpleName} - ${this.message}")
            ErrorUiState(
                message = "Something went wrong. Please try again later.",
                errorType = ErrorUiState.ErrorType.UNKNOWN
            )
        }
    }
}