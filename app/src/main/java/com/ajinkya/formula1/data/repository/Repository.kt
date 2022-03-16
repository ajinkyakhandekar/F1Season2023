package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.entity.ScheduleResponse
import retrofit2.Response

interface Repository {

    suspend fun getSchedule() : Response<ScheduleResponse>

}