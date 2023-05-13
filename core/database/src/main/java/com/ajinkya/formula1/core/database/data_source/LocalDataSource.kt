package com.ajinkya.formula1.core.database.data_source

import com.ajinkya.formula1.core.database.F1Dao
import com.ajinkya.formula1.core.database.entity.ConstructorEntity
import com.ajinkya.formula1.core.database.entity.DriverEntity
import com.ajinkya.formula1.core.database.entity.ScheduleEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val f1Dao: F1Dao
) {

    suspend fun insertSchedule(scheduleList: List<ScheduleEntity>) =
        f1Dao.insertSchedule(scheduleList)

    suspend fun insertDrivers(driverList: List<DriverEntity>) =
        f1Dao.insertDrivers(driverList)

    suspend fun insertConstructors(constructorList: List<ConstructorEntity>) =
        f1Dao.insertConstructors(constructorList)


    fun getSchedule() = f1Dao.getSchedule()

    fun getDrivers() = f1Dao.getDrivers()

    fun getConstructors() = f1Dao.getConstructors()

}
