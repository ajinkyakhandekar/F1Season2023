package com.ajinkya.formula1.data.remote.service

import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.remote.dto.ScheduleDto
import com.ajinkya.formula1.data.remote.dto.StandingsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.SEASON_2022)
    suspend fun getSchedule() : ScheduleDto

    @GET(Constant.STANDING_DRIVER_2022)
    suspend fun getDriverStandings() : Response<StandingsResponse>

    @GET(Constant.STANDING_CONSTRUCTOR_2022)
    suspend fun getConstructorStandings() : Response<StandingsResponse>

}