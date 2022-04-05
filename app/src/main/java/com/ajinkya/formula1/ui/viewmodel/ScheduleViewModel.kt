package com.ajinkya.formula1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.common.ResponseStatus
import com.ajinkya.formula1.common.ifNull
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

    private val _uiState = MutableStateFlow(ScheduleState())
    val uiState: StateFlow<ScheduleState> = _uiState

    init {
        getSchedule()
    }

    private fun getSchedule() {
        getScheduleUseCase().onEach { responseStatus ->
            when (responseStatus) {
                is ResponseStatus.Loading -> {
                    _uiState.value = ScheduleState(
                        true,
                        responseStatus.data.orEmpty(),
                    )
                }
                is ResponseStatus.Success -> {
                    _uiState.value = ScheduleState(
                        false,
                        responseStatus.data.orEmpty(),
                    )
                }
                is ResponseStatus.Error -> {
                    _uiState.value = ScheduleState(
                        false,
                        responseStatus.data.orEmpty(),
                        responseStatus.message.ifNull(Constant.MSG_ERROR)
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}