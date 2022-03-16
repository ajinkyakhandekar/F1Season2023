package com.ajinkya.formula1.data.repository

import com.ajinkya.formula1.data.service.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) : Repository {

    override suspend fun getSchedule()  = apiService.getSchedule()

}