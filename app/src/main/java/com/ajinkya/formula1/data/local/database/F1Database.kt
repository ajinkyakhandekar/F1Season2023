package com.ajinkya.formula1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajinkya.formula1.data.local.entity.Constructor
import com.ajinkya.formula1.data.local.entity.Driver
import com.ajinkya.formula1.data.local.entity.Schedule

@Database(
    entities = [Schedule::class, Driver::class, Constructor::class],
    version = 1,
    exportSchema = false
)
abstract class F1Database : RoomDatabase() {
    abstract fun f1Dao(): F1Dao
}
