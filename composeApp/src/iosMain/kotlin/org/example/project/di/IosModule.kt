package org.example.project.di


import org.example.project.data.local.datasource.StorageLocalDataSource
import org.example.project.data.local.datasource.StorageLocalDataSourceImpl
import org.koin.dsl.module

val iosModule = module {
    single<StorageLocalDataSource> {
        StorageLocalDataSourceImpl()
    }
}