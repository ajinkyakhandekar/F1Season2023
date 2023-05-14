package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.data.ResponseStatus
import com.ajinkya.formula1.core.model.Constructor
import com.ajinkya.formula1.core.model.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getSchedule(): Flow<List<Schedule>>
    fun loadSchedule(): Flow<ResponseStatus<List<Schedule>>>
}
