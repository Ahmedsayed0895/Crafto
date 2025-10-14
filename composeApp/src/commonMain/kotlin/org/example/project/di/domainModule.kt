package org.example.project.di

import org.example.project.domain.repository.CraftsmanRepository
import org.example.project.domain.usecase.craftsman.CreateCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.DeleteCraftsmanAccountUseCase
import org.example.project.domain.usecase.craftsman.GetCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.GetCraftsmanStatusUseCase
import org.example.project.domain.usecase.craftsman.UploadIdCardsUseCase
import org.example.project.domain.usecase.craftsman.UploadWorkPortfolioUseCase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
@ComponentScan("org.example.project.domain.usecase")
class DomainModule