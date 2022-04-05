package com.ajinkya.formula1.domain.use_case

import com.ajinkya.formula1.common.ResponseStatus
import com.ajinkya.formula1.data.repository.ScheduleRepository
import com.ajinkya.formula1.domain.model.Schedule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetScheduleUseCase(
    private val scheduleRepository: ScheduleRepository,
    private val formatScheduleDateUseCase: FormatScheduleDateUseCase,
    private val formatScheduleTimeUseCase: FormatScheduleTimeUseCase
) {

    operator fun invoke(): Flow<ResponseStatus<List<Schedule>>> {
        val scheduleResponse =   scheduleRepository.getSchedule().map {
            it.data?.map { schedule ->
                schedule.date = formatScheduleDateUseCase(schedule.date)
                schedule.time = formatScheduleTimeUseCase(schedule.time)
            }
            it
        }
        return scheduleResponse
    }

}