package com.ajinkya.formula1.core.network.data_source

import com.ajinkya.formula1.core.network.service.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getSchedule() = apiService.getSchedule()

    suspend fun getDrivers() = apiService.getDrivers()

    suspend fun getConstructors() = apiService.getConstructors()
}
