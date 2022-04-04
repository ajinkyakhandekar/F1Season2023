package com.ajinkya.formula1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.domain.use_case.GetScheduleUseCase
import com.ajinkya.formula1.ui.state.ScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScheduleState>(ScheduleState.Success(emptyList()))
    val uiState: StateFlow<ScheduleState> = _uiState

    init {
        getSchedule()
    }

    private fun getSchedule() {
        getScheduleUseCase().onEach { responseStatus ->
            when (responseStatus) {
                is ResponseStatus.Loading -> {
                    _uiState.value = ScheduleState.Loading(isLoading = true)
                }
                is ResponseStatus.Success -> {
                    _uiState.value = ScheduleState.Success(schedule = responseStatus.data ?: emptyList())
                }
                is ResponseStatus.Error -> {
                    _uiState.value = ScheduleState.Error(error = responseStatus.message ?: Constant.MSG_ERROR)
                }
            }
        }.launchIn(viewModelScope)
    }
}