package com.ajinkya.formula1.data.remote.data_source

import com.ajinkya.formula1.data.remote.service.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getSchedule() = apiService.getSchedule()

    suspend fun getDriverStandings() = apiService.getDriverStandings()

    suspend fun getConstructorStandings() = apiService.getConstructorStandings()

}