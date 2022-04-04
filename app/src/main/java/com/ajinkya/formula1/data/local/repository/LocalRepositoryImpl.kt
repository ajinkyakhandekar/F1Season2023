package com.ajinkya.formula1.data.local.repository

import com.ajinkya.formula1.data.local.database.F1Dao
import com.ajinkya.formula1.data.local.entity.ConstructorEntity
import com.ajinkya.formula1.data.local.entity.DriverEntity
import com.ajinkya.formula1.data.local.entity.ScheduleEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val f1Dao: F1Dao
) : LocalRepository {

    override suspend fun insertSchedule(scheduleEntityList: List<ScheduleEntity>) =
        f1Dao.insertSchedule(scheduleEntityList)

    override suspend fun insertDriverStandings(driverEntityList: List<DriverEntity>) =
        f1Dao.insertDriver(driverEntityList)

    override suspend fun insertConstructorStandings(constructorEntityList: List<ConstructorEntity>) =
        f1Dao.insertConstructor(constructorEntityList)


    override suspend fun getSchedule() = f1Dao.getSchedule()

    override fun getDriverStandings() = f1Dao.getDriverStandings()

    override fun getConstructorStandings() = f1Dao.getConstructorStandings()

}