package com.ajinkya.formula1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.common.ResponseStatus
import com.ajinkya.formula1.common.ifNull
import com.ajinkya.formula1.domain.use_case.GetDriverStandingsUseCase
import com.ajinkya.formula1.ui.state.DriverStandingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DriverStandingsViewModel @Inject constructor(
    private val getDriverStandingsUseCase: GetDriverStandingsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DriverStandingsState())
    val uiState: StateFlow<DriverStandingsState> = _uiState

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        getDriverStandingsUseCase().onEach { responseStatus ->
            when (responseStatus) {
                is ResponseStatus.Loading -> {
                    _uiState.value = DriverStandingsState(
                        true,
                        responseStatus.data.orEmpty(),
                    )
                }
                is ResponseStatus.Success -> {
                    _uiState.value = DriverStandingsState(
                        false,
                        responseStatus.data.orEmpty(),
                    )
                }
                is ResponseStatus.Error -> {
                    _uiState.value = DriverStandingsState(
                        false,
                        responseStatus.data.orEmpty(),
                        responseStatus.message.ifNull(Constant.MSG_ERROR)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}