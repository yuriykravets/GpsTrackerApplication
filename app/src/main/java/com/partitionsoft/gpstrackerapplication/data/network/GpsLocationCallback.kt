package com.partitionsoft.gpstrackerapplication.data.network

import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.partitionsoft.gpstrackerapplication.domain.model.LocationEntity
import com.partitionsoft.gpstrackerapplication.presentation.LocationViewModel

class GpsLocationCallback(private val locationViewModel: LocationViewModel) : LocationCallback() {
    override fun onLocationResult(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        location?.let {
            val locationEntity = LocationEntity(
                latitude = it.latitude,
                longitude = it.longitude,
                timestamp = System.currentTimeMillis()
            )
            locationViewModel.insertLocation(locationEntity)
            locationViewModel.locations.observeForever { locations ->
                if (locations.size >= 10) {
                    locationViewModel.sendLocationsToServer(locations)
                }
            }
        }
    }
}