package com.example.pregnacyvitalstrack

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {

    @Inject
     lateinit var hiltWorkerFactory: HiltWorkerFactory

    companion object{
        const val CHANNEL_ID = "PregenacyId"
        const val CHANNEL_NAME = "Pregenacy"
    }

    override fun onCreate() {
        super.onCreate()

        WorkManager.initialize(this,Configuration.Builder()
            .setWorkerFactory(hiltWorkerFactory)
            .build())

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val channel = NotificationChannel(
                 CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val notifi = this.getSystemService(NotificationManager::class.java)
            notifi.createNotificationChannel(channel)
        }
    }


}