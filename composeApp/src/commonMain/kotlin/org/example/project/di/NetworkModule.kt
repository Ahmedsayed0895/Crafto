package org.example.project.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

//192.168.1.15
@Module
class NetworkModule {
    @Single
    fun provideHttpClient(): HttpClient {
        return HttpClient(engine = getHttpEngine()) {
            defaultRequest {
                   url("http://10.0.2.2:8085/")
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }

            install(HttpTimeout) {
                connectTimeoutMillis = TIME_OUT_INTERVAL_MILLI
                requestTimeoutMillis = TIME_OUT_INTERVAL_MILLI
            }

            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    private companion object {
        const val TIME_OUT_INTERVAL_MILLI = 10_000L
    }
}