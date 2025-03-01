package com.example.pregnacyvitalstrack.domain.worker

interface VitalsWorkerRepo {

    suspend fun perodicWorkRequest()
}