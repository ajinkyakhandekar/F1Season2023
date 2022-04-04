package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.domain.model.Schedule
import com.ajinkya.formula1.ui.viewmodel.ResponseStatus
import kotlinx.coroutines.flow.Flow

interface F1Repository {

    fun geSchedule(): Flow<ResponseStatus<List<Schedule>>>

}