package org.example.project.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO

actual fun getHttpEngine(): HttpClientEngine = CIO.create()