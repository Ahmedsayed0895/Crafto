package org.example.project.di

import org.example.project.domain.usecase.GetCategoriesUseCase
import org.example.project.domain.usecase.craftsman.CreateCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.DeleteCraftsmanAccountUseCase
import org.example.project.domain.usecase.craftsman.GetCraftsmanProfileUseCase
import org.example.project.domain.usecase.craftsman.GetCraftsmanStatusUseCase
import org.example.project.domain.usecase.craftsman.UploadIdCardsUseCase
import org.example.project.domain.usecase.craftsman.UploadCraftsmanProfilePictureUseCase
import org.example.project.domain.usecase.craftsman.UploadWorkPortfolioUseCase
import org.example.project.domain.usecase.customer.CreateCustomerProfileUseCase
import org.example.project.domain.usecase.customer.DeleteCustomerAccountUseCase
import org.example.project.domain.usecase.customer.GetCustomerProfileUseCase
import org.example.project.domain.usecase.customer.UploadCustomerProfilePictureUseCase
import org.example.project.domain.usecase.location.GetDistrictsByGovernorateUseCase
import org.example.project.domain.usecase.location.GetGovernoratesUseCase
import org.example.project.domain.usecase.session.ClearUserSessionUseCase
import org.example.project.domain.usecase.session.GetUserSessionUseCase
import org.example.project.domain.usecase.session.MarkOnboardingCompleteUseCase
import org.example.project.domain.usecase.session.SaveUserTypeUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { CreateCraftsmanProfileUseCase(get(), get()) }
    factory { CreateCustomerProfileUseCase(get(), get()) }
    factory { UploadIdCardsUseCase(get(),get()) }
    factory { UploadWorkPortfolioUseCase(get()) }
    factory { GetCraftsmanProfileUseCase(get()) }
    factory { GetCustomerProfileUseCase(get()) }
    factory { GetCraftsmanStatusUseCase(get()) }
    factory { DeleteCraftsmanAccountUseCase(get()) }
    factory { DeleteCustomerAccountUseCase(get()) }
    factory { GetCategoriesUseCase(get())}
    factory { UploadCraftsmanProfilePictureUseCase(get(),get()) }
    factory { UploadCustomerProfilePictureUseCase(get(), get()) }
    factory { GetGovernoratesUseCase(get()) }
    factory { GetDistrictsByGovernorateUseCase(get()) }

    factory { GetUserSessionUseCase(get()) }
    factory { SaveUserTypeUseCase(get()) }
    factory { MarkOnboardingCompleteUseCase(get()) }
    factory { ClearUserSessionUseCase(get()) }

}