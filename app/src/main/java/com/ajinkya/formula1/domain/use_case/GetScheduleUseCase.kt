package com.ajinkya.formula1.domain.use_case

import com.ajinkya.formula1.common.Constant.DISPLAY_DATE_FORMAT
import com.ajinkya.formula1.common.Constant.DISPLAY_TIME_FORMAT
import com.ajinkya.formula1.common.Constant.REMOTE_DATE_FORMAT
import com.ajinkya.formula1.common.Constant.REMOTE_TIME_FORMAT
import com.ajinkya.formula1.data.local.entity.Schedule
import com.ajinkya.formula1.data.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetScheduleUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    private val formatDateTimeUseCase: FormatDateTimeUseCase
) {
    operator fun invoke(): Flow<List<Schedule>> {
        val scheduleResponse = scheduleRepository.getSchedule().map {
            it.map { schedule ->
                schedule.date = formatDateTimeUseCase(schedule.date, REMOTE_DATE_FORMAT, DISPLAY_DATE_FORMAT) ?: ""
                schedule.time = formatDateTimeUseCase(schedule.time, REMOTE_TIME_FORMAT, DISPLAY_TIME_FORMAT) ?: ""
            }
            it
        }
        return scheduleResponse
    }
}
