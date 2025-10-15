package org.example.project.di

import org.example.project.domain.usecase.craftsman.CreateCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.DeleteCraftsmanAccountUseCase
import org.example.project.domain.usecase.craftsman.GetCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.GetCraftsmanStatusUseCase
import org.example.project.domain.usecase.craftsman.UploadIdCardsUseCase
import org.example.project.domain.usecase.craftsman.UploadWorkPortfolioUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CreateCraftsmanProfileUseCase(get()) }
    factory { UploadIdCardsUseCase(get()) }
    factory { UploadWorkPortfolioUseCase(get()) }
    factory { GetCraftsmanProfileUseCase(get()) }
    factory { GetCraftsmanStatusUseCase(get()) }
    factory { DeleteCraftsmanAccountUseCase(get()) }
}