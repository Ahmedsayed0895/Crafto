package org.example.project.di

import org.example.project.data.local.datasource.StorageLocalDataSource
import org.example.project.data.local.datasource.DataStoreLocalDataSourceImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<StorageLocalDataSource> {
        DataStoreLocalDataSourceImp(androidContext())
    }
}