package com.ajinkya.formula1.domain.use_case

import com.ajinkya.formula1.data.repository.ScheduleRepository
import com.ajinkya.formula1.domain.model.Schedule
import com.ajinkya.formula1.ui.viewmodel.ResponseStatus
import kotlinx.coroutines.flow.Flow

class GetScheduleUseCase(private val scheduleRepository: ScheduleRepository) {

    operator fun invoke(): Flow<ResponseStatus<List<Schedule>>> {
        return scheduleRepository.getSchedule()
    }

}