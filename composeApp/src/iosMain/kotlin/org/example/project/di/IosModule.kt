package org.example.project.di


import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("org.example.project.data.datasource.local")
class IosModule