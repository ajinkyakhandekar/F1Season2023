package com.ajinkya.formula1.data.local.data_source

import com.ajinkya.formula1.data.local.database.F1Dao
import com.ajinkya.formula1.data.local.entity.ConstructorEntity
import com.ajinkya.formula1.data.local.entity.DriverEntity
import com.ajinkya.formula1.data.local.entity.ScheduleEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val f1Dao: F1Dao
) {

    suspend fun insertSchedule(scheduleEntityList: List<ScheduleEntity>) =
        f1Dao.insertSchedule(scheduleEntityList)

    suspend fun insertDriverStandings(driverEntityList: List<DriverEntity>) =
        f1Dao.insertDriver(driverEntityList)

    suspend fun insertConstructorStandings(constructorEntityList: List<ConstructorEntity>) =
        f1Dao.insertConstructor(constructorEntityList)


    suspend fun getSchedule() = f1Dao.getSchedule()

    suspend fun getDriverStandings() = f1Dao.getDriverStandings()

    suspend fun getConstructorStandings() = f1Dao.getConstructorStandings()

}