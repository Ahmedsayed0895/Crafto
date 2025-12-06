package org.example.project.di

import org.example.project.presentation.screens.auth.UserTypeSelectionViewModel
import org.example.project.presentation.screens.onboarding.OnboardingViewModel
import org.example.project.presentation.screens.setup.craftsmansetup.CraftsmanSetupViewModel
import org.example.project.presentation.screens.setup.customersetup.CustomerSetupViewModel
import org.example.project.presentation.screens.setup.location.LocationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        CraftsmanSetupViewModel(
            createCraftsmanUseCase = get(),
            uploadIdCardsUseCase = get(),
            uploadWorkPortfolioUseCase = get(),
            getCategoriesUseCase = get(),
            uploadCraftsmanProfilePictureUseCase = get(),
        )
    }
    viewModel {
        CustomerSetupViewModel(
            createCustomerUseCase = get(),
            uploadCustomerProfilePictureUseCase = get(),
            getCategoriesUseCase = get(),
            getGovernoratesUseCase = get(),
            getDistrictsByGovernorateUseCase = get()
        )
    }

    viewModel { LocationViewModel(get()) }
    viewModel { OnboardingViewModel(get(), get()) }
    viewModel { UserTypeSelectionViewModel(get()) }
}