package com.ajinkya.formula1.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajinkya.formula1.data.local.entity.Constructor
import com.ajinkya.formula1.data.local.entity.Driver
import com.ajinkya.formula1.data.local.entity.Schedule
import kotlinx.coroutines.flow.Flow

@Dao
interface F1Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(scheduleList: List<Schedule>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrivers(driverList: List<Driver>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConstructors(constructorList: List<Constructor>)

    @Query("SELECT * FROM schedule_table")
    fun getSchedule(): Flow<List<Schedule>>

    @Query("SELECT * FROM driver_table")
    fun getDrivers(): Flow<List<Driver>>

    @Query("SELECT * FROM constructor_table")
    fun getConstructors(): Flow<List<Constructor>>

}
