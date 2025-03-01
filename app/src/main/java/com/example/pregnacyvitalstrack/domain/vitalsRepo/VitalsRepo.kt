package com.example.pregnacyvitalstrack.domain.vitalsRepo

import com.example.pregnacyvitalstrack.data.vitalsData.VitalsData
import kotlinx.coroutines.flow.Flow

interface VitalsRepo {


    suspend fun insertOrUpdate(vitalsData: VitalsData)
    suspend fun deleteData(vitalsData: VitalsData)
    suspend fun getData(id:Int):VitalsData?
    fun getAllData() : Flow<List<VitalsData>>

}