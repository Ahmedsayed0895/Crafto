package org.example.project.data.remote.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                encodeDefaults = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            requestTimeoutMillis = ApiConstants.Timeouts.REQUEST
            connectTimeoutMillis = ApiConstants.Timeouts.CONNECT
            socketTimeoutMillis = ApiConstants.Timeouts.SOCKET
        }

        defaultRequest {
            header(ApiConstants.Headers.ACCEPT, ContentType.Application.Json)
        }
    }
}