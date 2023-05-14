package com.ajinkya.formula1.core.data.repository

import com.ajinkya.formula1.core.data.ResponseStatus
import com.ajinkya.formula1.core.model.Driver
import kotlinx.coroutines.flow.Flow

interface DriverRepository {
    fun getDrivers(): Flow<List<Driver>>
    fun loadDrivers(): Flow<ResponseStatus<List<Driver>>>
}
