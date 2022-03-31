package com.ajinkya.formula1.data.service

import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.data.entity.ScheduleResponse
import com.ajinkya.formula1.data.entity.StandingsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.SEASON_2022)
    suspend fun getSchedule() : Response<ScheduleResponse>

    @GET(Constant.STANDING_DRIVER_2022)
    suspend fun getDriverStandings() : Response<StandingsResponse>

    @GET(Constant.STANDING_CONSTRUCTOR_2022)
    suspend fun getConstructorStandings() : Response<StandingsResponse>

}