package com.example.pregnacyvitalstrack.presentance.screen.homeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pregnacyvitalstrack.data.vitalsData.VitalsData
import com.example.pregnacyvitalstrack.domain.vitalsRepo.VitalsRepo
import com.example.pregnacyvitalstrack.domain.worker.VitalsWorkerRepo
import com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent.HomeAction
import com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent.HomeState
import com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent.HomeStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val vitalsRepo: VitalsRepo,
    private val vitalsWorkerRepo: VitalsWorkerRepo
) : ViewModel() {


    var state by mutableStateOf(HomeState())
        private set

    private val _vitalsState = MutableStateFlow(HomeStateFlow())
    var vitalsState = _vitalsState.asStateFlow()
        .onStart { getData() }
        .stateIn(viewModelScope, SharingStarted.Eagerly, HomeStateFlow())

    fun resetAValue() {
        state = HomeState()
    }

    init {
        viewModelScope.launch {
            vitalsWorkerRepo.perodicWorkRequest()
        }
    }


    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.Bpm -> {
                state = state.copy(
                    bpm = action.bpm,
                    bpmValid = action.bpm.isNotBlank()
                )
            }

            is HomeAction.Kg -> {
                state = state.copy(
                    kg = action.kg,
                    kgValid = action.kg.isNotBlank()
                )
            }

            is HomeAction.Kicks -> {
                state = state.copy(
                    kicks = action.kicks,
                    kicksValid = action.kicks.isNotBlank()
                )
            }

            is HomeAction.MmHg -> {
                state = state.copy(
                    mmHg = action.mmHg,
                    mmHgValid = action.mmHg.isNotBlank()
                )
            }

            HomeAction.SubmitBtn -> {
                insertOrUpdate()
            }

            is HomeAction.CardClick -> {
                getData(id = action.id)
            }

            is HomeAction.OnDeleteClick -> {
                deleteData(vitalsData = action.vitalsData)
            }
        }
    }

    private fun deleteData(vitalsData: VitalsData?){
        viewModelScope.launch {
            if(vitalsData!=null){
                vitalsRepo.deleteData(vitalsData = vitalsData)
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            vitalsRepo.getAllData().collect { vitals ->
                _vitalsState.update {
                    it.copy(
                        vitalsItem = vitals
                    )
                }
            }
        }
    }


    private fun getData(id: String?) {
        viewModelScope.launch {
            if (id != null) {
                vitalsRepo.getData(id = id.toInt())?.apply {
                    state = state.copy(
                        id = id,
                        kg = kg,
                        bpm = bpm,
                        mmHg = mmhg,
                        kicks = kicks,
                        currentTime = dateTime,
                        kgValid = kg.isNotBlank(),
                        bpmValid = bpm.isNotBlank(),
                        mmHgValid = mmhg.isNotBlank(),
                        kicksValid = kicks.isNotBlank()
                    )
                    _vitalsState.update {
                        it.copy(vitals = this)
                    }
                }
            }
        }
    }

    private fun insertOrUpdate() {
        viewModelScope.launch(Dispatchers.IO) {
            if (vitalsState.value.vitals != null) {

                val update = VitalsData(
                    id = state.id?.toInt(),
                    bpm = state.bpm,
                    mmhg = state.mmHg,
                    kg = state.kg,
                    kicks = state.kicks,
                    dateTime = state.currentTime,
                )
                vitalsRepo.insertOrUpdate(vitalsData = update)

                resetAValue()

            } else {
                val insert = VitalsData(
                    bpm = state.bpm,
                    mmhg = state.mmHg,
                    kg = state.kg,
                    kicks = state.kicks,
                    dateTime = state.currentTime,
                )

                vitalsRepo.insertOrUpdate(vitalsData = insert)
                //TODO viewModel configuraction change value does't destroy so reset the value
                resetAValue()

            }
        }
    }
}
