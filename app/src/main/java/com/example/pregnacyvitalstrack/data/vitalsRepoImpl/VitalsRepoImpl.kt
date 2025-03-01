package com.example.pregnacyvitalstrack.data.vitalsRepoImpl

import com.example.pregnacyvitalstrack.data.vitalsData.VitalsDao
import com.example.pregnacyvitalstrack.data.vitalsData.VitalsData
import com.example.pregnacyvitalstrack.domain.vitalsRepo.VitalsRepo
import kotlinx.coroutines.flow.Flow

class VitalsRepoImpl(
    private val vitalsDao: VitalsDao
) : VitalsRepo{
    override suspend fun insertOrUpdate(vitalsData: VitalsData) {
        vitalsDao.insertOrUpdate(vitalsData)
    }

    override suspend fun deleteData(vitalsData: VitalsData) {
        vitalsDao.deleteData(vitalsData=vitalsData)
    }

    override suspend fun getData(id: Int): VitalsData? {
        return  vitalsDao.getData(id=id)
    }

    override fun getAllData(): Flow<List<VitalsData>> {
        return vitalsDao.getAllData()
    }
}