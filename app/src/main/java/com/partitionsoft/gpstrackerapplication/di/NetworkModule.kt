package com.partitionsoft.gpstrackerapplication.di

import com.partitionsoft.gpstrackerapplication.data.network.MockApiService
import org.koin.dsl.module

val networkModule = module {
    single { MockApiService.create() }
}