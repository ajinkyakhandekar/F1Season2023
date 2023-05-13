package com.ajinkya.formula1.feature.schedule

import com.ajinkya.formula1.data.local.entity.Schedule

data class ScheduleState(
    var schedule: List<Schedule> = emptyList()
)
