package com.ajinkya.formula1.ui.state

import com.ajinkya.formula1.domain.model.Schedule

data class ScheduleState(
    var isLoading: Boolean = false,
    var schedule: List<Schedule> = emptyList(),
    var errorMessage: String = ""
)