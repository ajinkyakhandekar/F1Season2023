package com.ajinkya.formula1.core.network.service

import com.ajinkya.formula1.core.common.Constant
import com.ajinkya.formula1.core.network.dto.ScheduleDto
import com.ajinkya.formula1.core.network.dto.StandingsDto
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.SCHEDULE)
    suspend fun getSchedule() : ScheduleDto

    @GET(Constant.STANDINGS_DRIVER)
    suspend fun getDrivers() : StandingsDto

    @GET(Constant.STANDINGS_CONSTRUCTOR)
    suspend fun getConstructors() : StandingsDto
}
