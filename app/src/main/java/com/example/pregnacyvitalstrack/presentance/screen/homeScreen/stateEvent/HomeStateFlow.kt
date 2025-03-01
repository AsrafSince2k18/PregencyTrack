package com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent

import androidx.compose.runtime.Immutable
import com.example.pregnacyvitalstrack.data.vitalsData.VitalsData

@Immutable
data class HomeStateFlow(

    val vitalsItem : List<VitalsData> = emptyList(),

    val vitals : VitalsData?=null,

    val id : Int?=null

)
