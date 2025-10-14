package org.example.project.data.mapper

import org.example.project.data.dto.ErrorResponseDto
import org.example.project.domain.exception.*

fun Exception.toDomainException(): CraftoException {
    return when (this) {
        is CraftoException -> this
        is IllegalArgumentException -> ValidationException(message ?: "Invalid input")
        is NoSuchElementException -> NotFoundException(message ?: "Resource not found")
        is IllegalStateException -> this.toIllegalStateError()
        else -> NetworkException(message ?: "Network error occurred")
    }
}

private fun IllegalStateException.toIllegalStateError(): CraftoException {
    return if (isAuthenticationError()) {
        UnauthorizedException(message?: "User not authenticated")
    } else {
        UnknownException(message ?: "Invalid state")
    }
}

private fun IllegalStateException.isAuthenticationError(): Boolean {
    return message?.contains("auth", ignoreCase = true) == true
}

fun ErrorResponseDto.toDomainException(): CraftoException {
    return when (code) {
        "CRAFTSMAN_EXISTS" -> AlreadyExistsException("Craftsman profile already exists")
        "INVALID_INPUT" -> ValidationException(message)
        "NOT_FOUND" -> NotFoundException(message)
        "FORBIDDEN" -> ForbiddenException(message)
        "UNAUTHORIZED" -> UnauthorizedException(message)
        else -> UnknownException(message)
    }
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Result<T> {
    return try {
        Result.success(apiCall())
    } catch (e: Exception) {
        Result.failure(e.toDomainException() as Exception)
    }
}