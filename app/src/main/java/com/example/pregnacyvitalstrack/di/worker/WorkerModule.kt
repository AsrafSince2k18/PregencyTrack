package com.example.pregnacyvitalstrack.di.worker

import android.app.Application
import androidx.work.WorkManager
import com.example.pregnacyvitalstrack.data.worker.VitalsWorkerRepoImpl
import com.example.pregnacyvitalstrack.domain.worker.VitalsWorkerRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {


    @Provides
    @Singleton
    fun provideWorkManager(application: Application):WorkManager{
        return WorkManager.getInstance(context=application)
    }

    @Provides
    @Singleton
    fun provideVitalsWorkerRepoImpl(workManager: WorkManager):VitalsWorkerRepo{
        return VitalsWorkerRepoImpl(workManager=workManager)
    }

}