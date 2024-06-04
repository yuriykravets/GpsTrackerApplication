package com.partitionsoft.gpstrackerapplication

import android.app.Application
import com.partitionsoft.gpstrackerapplication.di.databaseModule
import com.partitionsoft.gpstrackerapplication.di.locationModule
import com.partitionsoft.gpstrackerapplication.di.networkModule
import com.partitionsoft.gpstrackerapplication.di.repositoryModule
import com.partitionsoft.gpstrackerapplication.di.useCaseModule
import com.partitionsoft.gpstrackerapplication.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GpsTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GpsTrackerApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    locationModule
                )
            )
        }
    }
}