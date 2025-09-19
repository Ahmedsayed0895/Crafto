package org.example.project.di

import org.example.project.data.memory.dataSource.CategoryDataSourceImpl
import org.example.project.data.repository.CategoryRepositoryImpl
import org.example.project.data.repository.dataSource.CategoryDataSource
import org.example.project.domain.repository.CategoryRepository
import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.presentation.viewmodel.accountSetup.AccountSetupViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val sharedModules = module {
    //account setup screens - category
    singleOf(::CategoryRepositoryImpl) { bind<CategoryRepository>() }
    singleOf(::CategoryDataSourceImpl) { bind<CategoryDataSource>() }
    singleOf(::GetCategoriesUseCase)
    viewModelOf(::AccountSetupViewModel)
}

expect val platformModule: Module