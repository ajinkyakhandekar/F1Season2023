package com.ajinkya.formula1.feature.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.data.repository.ScheduleRepository
import com.ajinkya.formula1.domain.use_case.GetScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    private val getScheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ScheduleState())
    val state = _state.asStateFlow()

    init {
        getSchedule()
        loadSchedule()
    }

    private fun getSchedule() {
        viewModelScope.launch {
            getScheduleUseCase()
                .flowOn(Dispatchers.IO)
                .collect { schedule ->
                    _state.update {
                        it.copy(schedule = schedule)
                    }
                }
        }
    }

    private fun loadSchedule() {
        viewModelScope.launch {
            scheduleRepository.loadSchedule()
                .catch { }
                .collect()
        }
    }
}
