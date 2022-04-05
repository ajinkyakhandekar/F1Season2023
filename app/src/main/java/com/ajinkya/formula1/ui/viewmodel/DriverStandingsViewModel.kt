package com.ajinkya.formula1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.common.Constant
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

    private val _uiState = MutableStateFlow<DriverStandingsState>(DriverStandingsState.Success(emptyList()))
    val uiState: StateFlow<DriverStandingsState> = _uiState

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        getDriverStandingsUseCase().onEach { responseStatus ->
            when (responseStatus) {
                is ResponseStatus.Loading -> {
                    _uiState.value = DriverStandingsState.Loading(isLoading = true)
                }
                is ResponseStatus.Success -> {
                    _uiState.value = DriverStandingsState.Success(drivers = responseStatus.data ?: emptyList())
                }
                is ResponseStatus.Error -> {
                    _uiState.value = DriverStandingsState.Error(error = responseStatus.message ?: Constant.MSG_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }
}