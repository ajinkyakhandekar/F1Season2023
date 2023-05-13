package com.ajinkya.formula1.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajinkya.formula1.core.database.entity.ConstructorEntity
import com.ajinkya.formula1.core.database.entity.DriverEntity
import com.ajinkya.formula1.core.database.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface F1Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(scheduleList: List<ScheduleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrivers(driverList: List<DriverEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConstructors(constructorList: List<ConstructorEntity>)

    @Query("SELECT * FROM schedule_table")
    fun getSchedule(): Flow<List<ScheduleEntity>>

    @Query("SELECT * FROM driver_table")
    fun getDrivers(): Flow<List<DriverEntity>>

    @Query("SELECT * FROM constructor_table")
    fun getConstructors(): Flow<List<ConstructorEntity>>

}
