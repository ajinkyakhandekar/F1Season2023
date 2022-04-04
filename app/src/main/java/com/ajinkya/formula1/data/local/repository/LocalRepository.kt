package com.ajinkya.formula1.data.local.repository

import androidx.lifecycle.LiveData
import com.ajinkya.formula1.data.local.entity.ConstructorEntity
import com.ajinkya.formula1.data.local.entity.DriverEntity
import com.ajinkya.formula1.data.local.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertSchedule(scheduleEntityList: List<ScheduleEntity>)

    suspend fun insertDriverStandings(driverEntityList: List<DriverEntity>)

    suspend fun insertConstructorStandings(constructorEntityList: List<ConstructorEntity>)

    suspend fun getSchedule() : List<ScheduleEntity>

    fun getDriverStandings() : LiveData<MutableList<DriverEntity>>

    fun getConstructorStandings() : LiveData<MutableList<ConstructorEntity>>
}