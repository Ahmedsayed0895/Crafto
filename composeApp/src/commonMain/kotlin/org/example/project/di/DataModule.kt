package org.example.project.di

import io.ktor.client.HttpClient
import org.example.project.data.local.datasource.StorageLocalDataSource
import org.example.project.data.local.datasource.UserPreferences
import org.example.project.data.local.datasource.UserPreferencesImpl
import org.example.project.data.remote.datasource.CraftsmanRemoteDataSource
import org.example.project.data.remote.datasource.CraftsmanRemoteDataSourceImpl
import org.example.project.data.remote.network.ApiConstants
import org.example.project.data.repository.CraftsmanRepositoryImpl
import org.example.project.domain.repository.CraftsmanRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("org.example.project.data")
class DataModule