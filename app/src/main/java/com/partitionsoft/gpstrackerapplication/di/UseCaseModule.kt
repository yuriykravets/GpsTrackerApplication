package com.partitionsoft.gpstrackerapplication.di

import com.partitionsoft.gpstrackerapplication.domain.useCase.LocationUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { LocationUseCase(get()) }
}