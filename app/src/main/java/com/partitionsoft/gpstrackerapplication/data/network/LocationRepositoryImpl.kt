package com.partitionsoft.gpstrackerapplication.data.network

import androidx.lifecycle.LiveData
import com.partitionsoft.gpstrackerapplication.data.local.LocationDao
import com.partitionsoft.gpstrackerapplication.domain.model.LocationEntity

class LocationRepositoryImpl(
    private val locationDao: LocationDao,
    private val mockApiService: MockApiService
) : LocationRepository {

    override suspend fun insert(location: LocationEntity) {
        locationDao.insert(location)
    }

    override fun getAll(): LiveData<List<LocationEntity>> = locationDao.getAll()

    override suspend fun deleteAll() {
        locationDao.deleteAll()
    }

    override suspend fun sendLocationsToServer(locations: List<LocationEntity>) {
        try {
            val response = mockApiService.sendLocations(locations)
            if (response.isSuccessful) {
                locationDao.deleteAll()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}