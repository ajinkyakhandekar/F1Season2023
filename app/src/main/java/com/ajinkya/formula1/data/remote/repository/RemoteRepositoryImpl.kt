package com.ajinkya.formula1.data.remote.repository

import com.ajinkya.formula1.data.remote.service.ApiService
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val apiService: ApiService) : RemoteRepository {

    override suspend fun getSchedule()  = apiService.getSchedule()

    override suspend fun getDriverStandings()  = apiService.getDriverStandings()

    override suspend fun getConstructorStandings()  = apiService.getConstructorStandings()

}