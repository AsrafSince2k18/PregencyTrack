package com.example.pregnacyvitalstrack.data.worker

import android.Manifest
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pregnacyvitalstrack.MainActivity
import com.example.pregnacyvitalstrack.MyApp.Companion.CHANNEL_ID
import com.example.pregnacyvitalstrack.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


@HiltWorker
class VitalsWorkerClass @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters
) :CoroutineWorker(context,workerParameters){
    //TODO Assisted annotation inject at runtime so not use normal inject you can use AssistedInject
    override suspend fun doWork(): Result {
        return try {
           if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
               if (context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {

                   val intent = Intent(context, MainActivity::class.java).apply {
                       flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                   }

                   // Create a PendingIntent for the notification
                   val pendingIntent = PendingIntent.getActivity(
                       context,
                       0, // Request code
                       intent,
                       PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                   )
                   val notification = Notification.Builder(context, CHANNEL_ID)
                       .setContentTitle("Time to log your vitals!")
                       .setContentText("Stay on top of your health. Please update your vitals now!")
                       .setSmallIcon(R.drawable.ic_launcher_foreground)
                       .setContentIntent(pendingIntent) // Set the PendingIntent
                       .setAutoCancel(true)
                       .build()
                   context.getSystemService(NotificationManager::class.java)
                       .notify(1, notification)
               }
           }
            Result.success()
        }catch (e:Exception){
            Result.failure()
        }
    }


}