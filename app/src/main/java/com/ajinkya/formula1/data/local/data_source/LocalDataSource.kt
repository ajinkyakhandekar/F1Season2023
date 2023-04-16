package com.ajinkya.formula1.data.local.data_source

import com.ajinkya.formula1.data.local.database.F1Dao
import com.ajinkya.formula1.data.local.entity.Constructor
import com.ajinkya.formula1.data.local.entity.Driver
import com.ajinkya.formula1.data.local.entity.Schedule
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val f1Dao: F1Dao
) {

    suspend fun insertSchedule(scheduleList: List<Schedule>) =
        f1Dao.insertSchedule(scheduleList)

    suspend fun insertDrivers(driverList: List<Driver>) =
        f1Dao.insertDrivers(driverList)

    suspend fun insertConstructors(constructorList: List<Constructor>) =
        f1Dao.insertConstructors(constructorList)


    fun getSchedule() = f1Dao.getSchedule()

    fun getDrivers() = f1Dao.getDrivers()

    fun getConstructors() = f1Dao.getConstructors()

}
