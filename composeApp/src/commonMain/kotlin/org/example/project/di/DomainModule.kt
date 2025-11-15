package org.example.project.di

import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.domain.usecase.craftsman.CreateCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.DeleteCraftsmanAccountUseCase
import org.example.project.domain.usecase.craftsman.GetCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.GetCraftsmanStatusUseCase
import org.example.project.domain.usecase.craftsman.UploadIdCardsUseCase
import org.example.project.domain.usecase.craftsman.UploadProfilePictureUseCase
import org.example.project.domain.usecase.craftsman.UploadWorkPortfolioUseCase
import org.example.project.domain.usecase.session.ClearUserSessionUseCase
import org.example.project.domain.usecase.session.GetUserSessionUseCase
import org.example.project.domain.usecase.session.MarkOnboardingCompleteUseCase
import org.example.project.domain.usecase.session.SaveUserTypeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CreateCraftsmanProfileUseCase(get(), get()) }
    factory { UploadIdCardsUseCase(get(),get()) }
    factory { UploadWorkPortfolioUseCase(get()) }
    factory { GetCraftsmanProfileUseCase(get()) }
    factory { GetCraftsmanStatusUseCase(get()) }
    factory { DeleteCraftsmanAccountUseCase(get()) }
    factory { GetCategoriesUseCase(get())}
    factory { UploadProfilePictureUseCase(get(),get()) }

    factory { GetUserSessionUseCase(get()) }
    factory { SaveUserTypeUseCase(get()) }
    factory { MarkOnboardingCompleteUseCase(get()) }
    factory { ClearUserSessionUseCase(get()) }
}