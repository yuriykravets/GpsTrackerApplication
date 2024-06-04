package com.partitionsoft.gpstrackerapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partitionsoft.gpstrackerapplication.domain.model.LocationEntity
import com.partitionsoft.gpstrackerapplication.domain.useCase.LocationUseCase
import kotlinx.coroutines.launch

class LocationViewModel(private val useCase: LocationUseCase) : ViewModel() {
    val locations: LiveData<List<LocationEntity>> = useCase.getAllLocations()

    fun insertLocation(location: LocationEntity) {
        viewModelScope.launch {
            useCase.insertLocation(location)
        }
    }

    fun sendLocationsToServer(locations: List<LocationEntity>) {
        viewModelScope.launch {
            useCase.sendLocationsToServer(locations)
        }
    }
}
