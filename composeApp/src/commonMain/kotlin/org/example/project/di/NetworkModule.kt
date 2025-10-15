package org.example.project.di

import org.example.project.data.remote.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { getHttpEngine() }
    single { createHttpClient(get()) }
}