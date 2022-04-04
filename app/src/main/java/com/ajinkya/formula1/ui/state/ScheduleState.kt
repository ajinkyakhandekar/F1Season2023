package com.ajinkya.formula1.ui.state

import com.ajinkya.formula1.domain.model.Schedule

sealed class ScheduleState {
    data class Loading(var isLoading: Boolean): ScheduleState()
    data class Success(var schedule: List<Schedule>): ScheduleState()
    data class Error(var error: String): ScheduleState()
}
