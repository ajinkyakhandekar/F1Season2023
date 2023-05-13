package com.ajinkya.formula1.feature.schedule

import com.ajinkya.formula1.core.model.Schedule

data class ScheduleState(
    var schedule: List<Schedule> = emptyList()
)
