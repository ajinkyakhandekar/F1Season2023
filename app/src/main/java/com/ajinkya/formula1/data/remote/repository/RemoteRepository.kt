package com.ajinkya.formula1.data.remote.repository

import com.ajinkya.formula1.data.remote.dto.ScheduleDto
import com.ajinkya.formula1.data.remote.dto.StandingsResponse
import retrofit2.Response

interface RemoteRepository {

    suspend fun getSchedule() : ScheduleDto

    suspend fun getDriverStandings() : Response<StandingsResponse>

    suspend fun getConstructorStandings() : Response<StandingsResponse>

}