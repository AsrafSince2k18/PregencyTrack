package com.example.pregnacyvitalstrack.di.vitals

import com.example.pregnacyvitalstrack.data.vitalsData.VitalsDatabase
import com.example.pregnacyvitalstrack.data.vitalsRepoImpl.VitalsRepoImpl
import com.example.pregnacyvitalstrack.domain.vitalsRepo.VitalsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object VitalsModule {


    @Provides
    @Singleton
    fun provideVitalsRepoImpl(vitalsDatabase: VitalsDatabase):VitalsRepo{
        return VitalsRepoImpl(vitalsDao = vitalsDatabase.vitalsDao())
    }

}