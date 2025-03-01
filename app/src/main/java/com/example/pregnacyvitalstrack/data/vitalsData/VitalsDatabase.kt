package com.example.pregnacyvitalstrack.data.vitalsData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VitalsData::class], version = 1)
abstract class VitalsDatabase : RoomDatabase(){

    abstract fun vitalsDao() :VitalsDao

}