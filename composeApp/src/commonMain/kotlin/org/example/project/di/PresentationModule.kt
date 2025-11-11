package org.example.project.di

import org.example.project.presentation.screens.setup.craftsmansetup.CraftsmanSetupViewModel
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
            uploadProfilePictureUseCase = get(),
        )
    }
    viewModel {LocationViewModel(get())}
}