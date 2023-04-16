package com.ajinkya.formula1.presentation.schedule

import com.ajinkya.formula1.data.local.entity.Schedule

data class ScheduleState(
    var schedule: List<Schedule> = emptyList()
)
