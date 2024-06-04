package com.partitionsoft.gpstrackerapplication.di

import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.partitionsoft.gpstrackerapplication.data.network.GpsLocationCallback
import com.partitionsoft.gpstrackerapplication.presentation.LocationViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val UPDATE_INTERVAL_MILLIS = 300000L

val locationModule = module {
    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    single {
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, UPDATE_INTERVAL_MILLIS).apply {
            setMinUpdateIntervalMillis(UPDATE_INTERVAL_MILLIS)
        }.build()
    }

    single<GpsLocationCallback> { (locationViewModel: LocationViewModel) ->
        GpsLocationCallback(locationViewModel)
    }
}