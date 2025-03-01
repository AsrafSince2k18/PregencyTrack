package com.example.pregnacyvitalstrack.data.onBoardRepoImpl

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.pregnacyvitalstrack.domain.onBoardRepo.OnBoardRepo
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class OnBoardRepoImpl @Inject constructor(
    private val context: Context
) : OnBoardRepo{

    private val Context.dataStore by preferencesDataStore("onBoard")

    override suspend fun saveOnBoard(save: Boolean) {
        context.dataStore
            .edit {
                it.set(key = booleanPreferencesKey("key"), value = save)
            }
    }

    override suspend fun getOnBoard(): Boolean {
        return context.dataStore.data
            .map {
                it.contains(booleanPreferencesKey("key"))
            }.first()
    }


}