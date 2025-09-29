package org.example.project.di

import LocationRepositoryImpl
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.project.domain.repository.LocationRepository
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient{
            defaultRequest {
                url("http://192.168.1.188:8085/")
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("Http client: $message")
                    }
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 150000000
            }
            defaultRequest {
            }
        }
    }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
}

