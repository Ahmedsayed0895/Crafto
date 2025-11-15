package org.example.project.di


import org.example.project.domain.repository.UserPreferences
import org.example.project.data.remote.datasource.CategoryDataSource
import org.example.project.data.remote.datasource.CraftsmanRemoteDataSource
import org.example.project.data.local.datasource.CategoryMemoryDataSource
import org.example.project.data.repository.UserPreferencesImpl
import org.example.project.data.memory.categorySeed
import org.example.project.data.remote.datasource.CraftsmanRemoteDataSourceImpl
import org.example.project.data.repository.CategoryRepositoryImpl
import org.example.project.data.repository.CraftsmanRepositoryImpl
import org.example.project.data.repository.LocationRepositoryImpl
import org.example.project.data.repository.OnboardingRepositoryImp
import org.example.project.data.service.ValidationServiceImpl
import org.example.project.domain.repository.CategoryRepository
import org.example.project.domain.repository.CraftsmanRepository
import org.example.project.domain.repository.LocationRepository
import org.example.project.domain.repository.OnboardingRepository
import org.example.project.domain.service.ValidationService
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
    single<OnboardingRepository> {
        OnboardingRepositoryImp(get())
    }
    single { categorySeed }
    single<CategoryDataSource> { CategoryMemoryDataSource(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single<ValidationService> { ValidationServiceImpl() }
    single<LocationRepository> { LocationRepositoryImpl(get()) }

}