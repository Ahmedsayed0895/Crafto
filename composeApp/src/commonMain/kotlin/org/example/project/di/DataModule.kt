package org.example.project.di


import org.example.project.data.local.datasource.UserPreferences
import org.example.project.data.local.datasource.UserPreferencesImpl
import org.example.project.data.remote.datasource.CraftsmanRemoteDataSource
import org.example.project.data.remote.datasource.CraftsmanRemoteDataSourceImpl
import org.example.project.data.repository.CraftsmanRepositoryImpl
import org.example.project.domain.repository.CraftsmanRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserPreferences> { UserPreferencesImpl(get()) }
    single<CraftsmanRemoteDataSource> { CraftsmanRemoteDataSourceImpl(get()) }
    single<CraftsmanRepository> {
        CraftsmanRepositoryImpl(
            remoteDataSource = get(),
            userPreferences = get()
        )
    }
}