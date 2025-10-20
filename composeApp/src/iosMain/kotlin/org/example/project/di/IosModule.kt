package org.example.project.di


import org.example.project.data.datasource.local.StorageLocalDataSource
import org.example.project.data.local.datasource.StorageLocalDataSourceImpl
import org.koin.dsl.module

val iosModule = module {
    single<StorageLocalDataSource> {
        StorageLocalDataSourceImpl()
    }
}