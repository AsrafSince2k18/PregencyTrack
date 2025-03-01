package com.example.pregnacyvitalstrack.di.onBoard

import android.app.Application
import com.example.pregnacyvitalstrack.data.onBoardRepoImpl.OnBoardRepoImpl
import com.example.pregnacyvitalstrack.domain.onBoardRepo.OnBoardRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object OnBoardModule {


    @Provides
    @Singleton
    fun onBoardRepoImpl(application: Application) : OnBoardRepo{
        return OnBoardRepoImpl(context = application)
    }

}