package com.ajinkya.formula1.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajinkya.formula1.data.local.entity.ConstructorEntity
import com.ajinkya.formula1.data.local.entity.DriverEntity
import com.ajinkya.formula1.data.local.entity.ScheduleEntity


@Dao
interface F1Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(scheduleEntityList: List<ScheduleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDriver(driverEntityList: List<DriverEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConstructor(constructorEntityList: List<ConstructorEntity>)

    @Query("SELECT * FROM schedule_table")
    suspend fun getSchedule(): List<ScheduleEntity>

    @Query("SELECT * FROM driver_table")
    suspend fun getDriverStandings(): List<DriverEntity>

    @Query("SELECT * FROM constructor_table")
    suspend fun getConstructorStandings(): List<ConstructorEntity>

}