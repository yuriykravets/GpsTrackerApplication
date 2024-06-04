package com.partitionsoft.gpstrackerapplication.domain.useCase

import com.partitionsoft.gpstrackerapplication.data.network.LocationRepository
import com.partitionsoft.gpstrackerapplication.domain.model.LocationEntity

class LocationUseCase(private val repository: LocationRepository) {
    fun getAllLocations() = repository.getAll()

    suspend fun insertLocation(location: LocationEntity) {
        repository.insert(location)
    }

    suspend fun sendLocationsToServer(locations: List<LocationEntity>) {
        repository.sendLocationsToServer(locations)
    }
}
