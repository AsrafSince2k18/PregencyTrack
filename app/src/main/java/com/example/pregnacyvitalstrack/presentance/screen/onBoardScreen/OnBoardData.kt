package com.example.pregnacyvitalstrack.presentance.screen.onBoardScreen

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class OnBoardData(
    @DrawableRes
    val image : Int,
    val color : Color,
    val txColor : Color,
    val primaryText:String,
    val secondaryText : String,
)
