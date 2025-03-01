package com.example.pregnacyvitalstrack.data.vitalsData

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface VitalsDao {

    @Upsert
    suspend fun insertOrUpdate(vitalsData: VitalsData)

    @Delete
    suspend fun deleteData(vitalsData: VitalsData)

    @Query("SELECT * FROM vitalsdata WHERE id LIKE(:id)")
    suspend fun getData(id:Int) : VitalsData?

    @Query("SELECT * FROM vitalsdata ORDER BY id DESC")
    fun getAllData() : Flow<List<VitalsData>>

}