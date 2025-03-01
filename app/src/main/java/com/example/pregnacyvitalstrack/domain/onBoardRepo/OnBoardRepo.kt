package com.example.pregnacyvitalstrack.domain.onBoardRepo

interface OnBoardRepo {

    suspend fun saveOnBoard(save:Boolean)
    suspend fun getOnBoard() : Boolean

}