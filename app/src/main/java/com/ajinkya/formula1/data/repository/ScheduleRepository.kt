package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.local.entity.Schedule
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun getSchedule(): Flow<List<Schedule>>
    suspend fun loadSchedule(): Flow<String>
}
