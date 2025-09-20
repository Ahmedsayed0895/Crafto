package org.example.project.di

import org.example.project.data.datasource.local.IdentityLocalDataSource
import org.example.project.data.datasource.local.PortfolioLocalDataSource
import org.example.project.data.datasource.local.StorageLocalDataSource
import org.example.project.data.datasource.remote.StorageRemoteDataSource
import org.example.project.data.local.datasource.DataStoreLocalDataSourceImp
import org.example.project.data.local.datasource.IdentityLocalDataSourceImp
import org.example.project.data.local.datasource.PortfolioLocalDataSourceImp
import org.example.project.data.remote.datasource.FirebaseStorageDataSource
import org.example.project.data.repository.IdentityRepositoryImp
import org.example.project.data.repository.PortfolioRepositoryImp
import org.example.project.domain.repository.IdentityRepository
import org.example.project.domain.repository.PortfolioRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    // Remote DataSources
    single<StorageRemoteDataSource> {
        FirebaseStorageDataSource()
    }

    // Local DataSources
    single<StorageLocalDataSource> {
        DataStoreLocalDataSourceImp(androidContext())
    }

    single<PortfolioLocalDataSource> {
        PortfolioLocalDataSourceImp(get())
    }

    single<IdentityLocalDataSource> {
        IdentityLocalDataSourceImp(get())
    }

    // Repositories
    single<PortfolioRepository> {
        PortfolioRepositoryImp(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<IdentityRepository> {
        IdentityRepositoryImp(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

}