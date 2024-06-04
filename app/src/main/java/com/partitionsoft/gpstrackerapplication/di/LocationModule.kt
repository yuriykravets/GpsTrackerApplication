package com.partitionsoft.gpstrackerapplication.di

import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.partitionsoft.gpstrackerapplication.data.network.GpsLocationCallback
import com.partitionsoft.gpstrackerapplication.presentation.LocationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val locationModule = module {
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    single {
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 300000).apply {
            setMinUpdateIntervalMillis(300000)
        }.build()
    }

    single<GpsLocationCallback> { (locationViewModel: LocationViewModel) ->
        GpsLocationCallback(locationViewModel)
    }
}