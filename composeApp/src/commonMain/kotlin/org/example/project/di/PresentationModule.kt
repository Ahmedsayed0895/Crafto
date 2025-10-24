package org.example.project.di

import org.example.project.presentation.viewmodel.craftsmansetup.CraftsmanSetupViewModel
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
}