package com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent

import com.example.pregnacyvitalstrack.data.vitalsData.VitalsData

sealed interface HomeAction {

    data class Bpm(val bpm : String) : HomeAction
    data class MmHg(val mmHg : String) : HomeAction
    data class Kg(val kg : String) : HomeAction
    data class Kicks(val kicks : String) : HomeAction

    data object SubmitBtn : HomeAction
    data class CardClick(val id : String):HomeAction
    data class OnDeleteClick(val vitalsData : VitalsData):HomeAction

}