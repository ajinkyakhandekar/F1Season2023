package com.ajinkya.formula1.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ajinkya.formula1.data.local.entity.ConstructorEntity
import com.ajinkya.formula1.data.local.entity.DriverEntity
import com.ajinkya.formula1.data.local.entity.ScheduleEntity
import kotlinx.coroutines.flow.Flow


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
    fun getDriverStandings(): LiveData<MutableList<DriverEntity>>

    @Query("SELECT * FROM constructor_table")
    fun getConstructorStandings(): LiveData<MutableList<ConstructorEntity>>

}