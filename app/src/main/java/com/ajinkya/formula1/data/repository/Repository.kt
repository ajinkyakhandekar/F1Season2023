package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.entity.ScheduleResponse
import com.ajinkya.formula1.data.entity.StandingsResponse
import retrofit2.Response

interface Repository {

    suspend fun getSchedule() : Response<ScheduleResponse>

    suspend fun getDriverStandings() : Response<StandingsResponse>

    suspend fun getConstructorStandings() : Response<StandingsResponse>

}