package org.example.project.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun getHttpEngine(): HttpClientEngine = Darwin.create{
    configureRequest {
        setAllowsCellularAccess(true)
    }
}