package com.partitionsoft.gpstrackerapplication.di

import com.partitionsoft.gpstrackerapplication.presentation.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LocationViewModel(get()) }
}