package org.example.project.presentation.mapper

import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import org.example.project.domain.exception.AlreadyExistsException
import org.example.project.domain.exception.ApiException
import org.example.project.domain.exception.ForbiddenException
import org.example.project.domain.exception.NetworkException
import org.example.project.domain.exception.NotFoundException
import org.example.project.domain.exception.ServerUnavailableException
import org.example.project.domain.exception.UnauthorizedException
import org.example.project.domain.exception.ValidationException
import org.example.project.presentation.shared.base.ErrorUiState
import org.example.project.util.AppLogger

fun Throwable.toErrorUiState(): ErrorUiState {
    return when (this) {
        // ========== CLIENT-SIDE: NETWORK ISSUES ==========
        is NetworkException,
        is SocketTimeoutException,
        is HttpRequestTimeoutException -> ErrorUiState(
            message = "Please check your internet connection and try again.",
            errorType = ErrorUiState.ErrorType.NETWORK
        )
        is ValidationException -> ErrorUiState(
            message = message ?: "Please check your input.",
            errorType = ErrorUiState.ErrorType.VALIDATION
        )

        // ========== SERVER-SIDE: SERVER UNAVAILABLE ==========
        is ServerUnavailableException,
        is ConnectTimeoutException -> ErrorUiState(
            message = message ?: "Server is currently unavailable. Please try again later.",
            errorType = ErrorUiState.ErrorType.SERVER
        )

        // ========== SERVER-SIDE: API ERRORS ==========
        is UnauthorizedException -> ErrorUiState(
            message = message ?: "Please login to continue.",
            errorType = ErrorUiState.ErrorType.AUTHENTICATION
        )
        is ForbiddenException -> ErrorUiState(
            message = message ?: "You don't have permission to perform this action.",
            errorType = ErrorUiState.ErrorType.AUTHENTICATION
        )
        is NotFoundException -> ErrorUiState(
            message = message ?: "The requested resource was not found.",
            errorType = ErrorUiState.ErrorType.SERVER
        )
        is AlreadyExistsException -> ErrorUiState(
            message = message ?: "This resource already exists.",
            errorType = ErrorUiState.ErrorType.SERVER
        )
        is ApiException -> ErrorUiState(
            message = message ?: "Server error occurred. Please try again.",
            errorType = ErrorUiState.ErrorType.SERVER
        )

        // ========== UNKNOWN ERRORS ==========
        else -> {
            AppLogger.e("ThrowableEX", this.message ?: "Unknown error")
            println("⚠️ Unexpected error: ${this::class.simpleName} - ${this.message}")
            ErrorUiState(
                message = message ?: "Something went wrong. Please try again later.",
                errorType = ErrorUiState.ErrorType.UNKNOWN
            )
        }
    }
}