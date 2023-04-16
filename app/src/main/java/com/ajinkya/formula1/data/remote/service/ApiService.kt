package com.ajinkya.formula1.data.remote.service

import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.remote.dto.ScheduleDto
import com.ajinkya.formula1.data.remote.dto.StandingsDto
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.SCHEDULE)
    suspend fun getSchedule() : ScheduleDto

    @GET(Constant.STANDINGS_DRIVER)
    suspend fun getDrivers() : StandingsDto

    @GET(Constant.STANDINGS_CONSTRUCTOR)
    suspend fun getConstructors() : StandingsDto
}
