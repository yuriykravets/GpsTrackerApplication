package com.partitionsoft.gpstrackerapplication.di

import com.partitionsoft.gpstrackerapplication.data.network.LocationRepository
import com.partitionsoft.gpstrackerapplication.data.network.LocationRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<LocationRepository> { LocationRepositoryImpl(get(), get()) }
}