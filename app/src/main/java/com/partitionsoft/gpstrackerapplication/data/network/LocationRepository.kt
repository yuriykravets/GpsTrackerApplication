package com.partitionsoft.gpstrackerapplication.data.network

import androidx.lifecycle.LiveData
import com.partitionsoft.gpstrackerapplication.domain.model.LocationEntity

interface LocationRepository {

    suspend fun insert(location: LocationEntity)

    fun getAll(): LiveData<List<LocationEntity>>

    suspend fun deleteAll()

    suspend fun sendLocationsToServer(locations: List<LocationEntity>)
}