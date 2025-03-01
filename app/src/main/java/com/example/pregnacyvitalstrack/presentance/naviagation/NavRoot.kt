package com.example.pregnacyvitalstrack.presentance.naviagation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoot {
    @Serializable
    data object OnBoardScreen : NavRoot
    @Serializable
    data object HomeScreen : NavRoot

}