package com.example.pregnacyvitalstrack.data.vitalsData

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class VitalsData(
    @PrimaryKey(autoGenerate = true)
    val id : Int?=null,
    val bpm : String,
    val mmhg : String,
    val kg :String,
    val kicks : String,
    val dateTime : Long,
)
