package com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent

data class HomeState(
    val id:String?=null,
    val bpm :String ="",
    val bpmValid:Boolean=false,
    val mmHg : String ="",
    val mmHgValid : Boolean =false,
    val kg : String ="",
    val kgValid : Boolean =false,
    val kicks :String ="",
    val kicksValid :Boolean =false,

    val currentTime : Long = System.currentTimeMillis(),
){
    val required
        get() =  bpmValid && mmHgValid &&kgValid && kicksValid
}