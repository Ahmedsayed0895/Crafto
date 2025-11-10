package org.example.project.di

import org.example.project.presentation.screens.setupScreens.location.LocationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class ViewModelModule {
    val module = module {
        viewModel { LocationViewModel(get()) }
    }
}