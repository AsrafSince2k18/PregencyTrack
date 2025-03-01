package com.example.pregnacyvitalstrack.data.worker

import android.annotation.SuppressLint
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.pregnacyvitalstrack.domain.worker.VitalsWorkerRepo
import java.time.Duration

class VitalsWorkerRepoImpl (
    private val workManager: WorkManager
): VitalsWorkerRepo{

    @SuppressLint("NewApi")
    val perodicRequst = PeriodicWorkRequestBuilder<VitalsWorkerClass>(
        Duration.ofHours(5)
    ).build()

    override suspend fun perodicWorkRequest() {
        workManager.enqueue(perodicRequst)
    }


}