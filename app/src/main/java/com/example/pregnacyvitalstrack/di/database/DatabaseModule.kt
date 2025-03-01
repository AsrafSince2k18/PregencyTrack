package com.example.pregnacyvitalstrack.di.database

import android.app.Application
import androidx.room.Room
import com.example.pregnacyvitalstrack.data.vitalsData.VitalsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application
    ):VitalsDatabase{
        return Room.databaseBuilder(application,VitalsDatabase::class.java,"VitalsDB")
            .build()
    }

}