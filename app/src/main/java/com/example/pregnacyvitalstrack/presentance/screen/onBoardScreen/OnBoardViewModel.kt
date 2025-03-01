package com.example.pregnacyvitalstrack.presentance.screen.onBoardScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pregnacyvitalstrack.domain.onBoardRepo.OnBoardRepo
import com.example.pregnacyvitalstrack.presentance.naviagation.NavRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val onBoardRepo: OnBoardRepo
) : ViewModel() {

     var state = mutableStateOf(true)
            private set


    var root by mutableStateOf<NavRoot>(NavRoot.OnBoardScreen)
        private set

    init {
     onBoardLogin()
    }

    fun onEvent(event: OnBoardAction) {
        when (event) {
            OnBoardAction.GetStarted -> {
                viewModelScope.launch {
                    onBoardRepo.saveOnBoard(true)
                }
            }
        }
    }

    private fun onBoardLogin() {
        viewModelScope.launch {
            onBoardRepo.getOnBoard().let { check ->
                root = if (check) {
                    NavRoot.HomeScreen
                } else {
                    NavRoot.OnBoardScreen
                }
                delay(400)
                state.value=false
            }
        }
    }

}