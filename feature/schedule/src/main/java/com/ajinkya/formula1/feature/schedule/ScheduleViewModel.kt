package com.ajinkya.formula1.feature.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.core.data.repository.ScheduleRepository
import com.ajinkya.formula1.core.domain.GetScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    private val getScheduleUseCase: GetScheduleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ScheduleState())
    val state = _state.asStateFlow()

    fun getSchedule() {
        viewModelScope.launch {
            getScheduleUseCase()
                .collect { schedule ->
                    _state.update {
                        it.copy(schedule = schedule)
                    }
                }
        }
    }
}
