package com.partitionsoft.gpstrackerapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.partitionsoft.gpstrackerapplication.domain.model.LocationEntity

@Dao
interface LocationDao {
    @Insert
    suspend fun insert(location: LocationEntity)

    @Query("SELECT * FROM location")
    fun getAll(): LiveData<List<LocationEntity>>

    @Query("DELETE FROM location")
    suspend fun deleteAll()
}