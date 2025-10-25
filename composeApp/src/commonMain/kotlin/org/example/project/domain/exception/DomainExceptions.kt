package org.example.project.domain.exception

sealed class CraftoException(message: String?) : Exception(message)

class UnauthorizedException(message: String? = "User not authenticated") : CraftoException(message)
class ForbiddenException(message: String? = "Access denied") : CraftoException(message)
class NotFoundException(message: String?) : CraftoException(message)
class AlreadyExistsException(message: String?) : CraftoException(message)
class ValidationException(message: String?) : CraftoException(message)
class NetworkException(message: String? = "Network error occurred") : CraftoException(message)
class ApiException(message: String?) : CraftoException(message)
class ServerUnavailableException(message: String? = "Server is currently unavailable") : CraftoException(message)
class UnknownException(message: String? = "Unknown error occurred") : CraftoException(message)