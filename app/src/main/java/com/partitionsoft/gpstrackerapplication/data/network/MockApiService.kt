package com.partitionsoft.gpstrackerapplication.data.network

import com.partitionsoft.gpstrackerapplication.common.BASE_URL
import com.partitionsoft.gpstrackerapplication.common.LOCATIONS_ENDPOINT
import com.partitionsoft.gpstrackerapplication.domain.model.LocationEntity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MockApiService {
    @POST(LOCATIONS_ENDPOINT)
    suspend fun sendLocations(@Body locations: List<LocationEntity>): Response<Void>

    companion object {
        fun create(): MockApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(MockApiService::class.java)
        }
    }
}
