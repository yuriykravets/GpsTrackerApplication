package com.partitionsoft.gpstrackerapplication.di

import com.partitionsoft.gpstrackerapplication.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { AppDatabase.getInstance(androidContext()).locationDao() }
}
