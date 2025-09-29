package org.example.project.di

import org.example.project.presentation.screen.LocationViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val locationModule = module {
    viewModelOf(::LocationViewModel)
}