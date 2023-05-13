package com.ajinkya.formula1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.core.common.Constant

@Entity(tableName = com.ajinkya.formula1.core.common.Constant.DRIVER_TABLE)
data class Driver(
    @PrimaryKey
    val position: String,
    val points: String,
    val constructorName: String,
    val driverName: String,
)
