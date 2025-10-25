package org.example.project.data.remote.network

import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.example.project.data.remote.dto.ErrorResponseDto
import org.example.project.domain.exception.AlreadyExistsException
import org.example.project.domain.exception.ApiException
import org.example.project.domain.exception.CraftoException
import org.example.project.domain.exception.ForbiddenException
import org.example.project.domain.exception.NetworkException
import org.example.project.domain.exception.NotFoundException
import org.example.project.domain.exception.ServerUnavailableException
import org.example.project.domain.exception.UnauthorizedException

suspend inline fun <reified T> wrapApiCall(
    apiCall: suspend () -> HttpResponse
): T {
    try {
        val response = apiCall()

        return when (response.status) {
            HttpStatusCode.OK, HttpStatusCode.Created -> {
                response.body<T>()
            }
            HttpStatusCode.Unauthorized -> {
                throw UnauthorizedException("Authentication required")
            }
            HttpStatusCode.Forbidden -> {
                throw ForbiddenException("Access denied")
            }
            HttpStatusCode.NotFound -> {
                throw NotFoundException("Resource not found")
            }
            HttpStatusCode.Conflict -> {
                val error = try {
                    response.body<ErrorResponseDto>()
                } catch (e: Exception) {
                    null
                }
                throw AlreadyExistsException(error?.message ?: "Resource already exists")
            }
            HttpStatusCode.InternalServerError,
            HttpStatusCode.BadGateway,
            HttpStatusCode.ServiceUnavailable,
            HttpStatusCode.GatewayTimeout -> {
                throw ServerUnavailableException("Server is experiencing issues. Please try again later.")
            }
            else -> {
                val error = try {
                    response.body<ErrorResponseDto>()
                } catch (e: Exception) {
                    null
                }
                throw ApiException(error?.message ?: "API error: ${response.status.value}")
            }
        }
    } catch (e: CraftoException) {
        throw e
    } catch (e: ConnectTimeoutException) {
        throw ServerUnavailableException("Cannot connect to server. Please try again later.")
    } catch (e: SocketTimeoutException) {
        throw NetworkException("Connection timeout. Please check your internet connection.")
    } catch (e: HttpRequestTimeoutException) {
        throw NetworkException("Request timeout. Please try again.")
    }catch (e: Exception) {
        throw NetworkException("Network error: ${e.message}")
    }
}