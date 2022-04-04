package com.ajinkya.formula1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajinkya.formula1.data.local.entity.ConstructorEntity
import com.ajinkya.formula1.data.local.entity.DriverEntity
import com.ajinkya.formula1.data.local.entity.ScheduleEntity

@Database(
    entities = [ScheduleEntity::class, DriverEntity::class, ConstructorEntity::class],
    version = 1,
    exportSchema = false
)
abstract class F1Database : RoomDatabase() {
    abstract fun f1Dao(): F1Dao
}