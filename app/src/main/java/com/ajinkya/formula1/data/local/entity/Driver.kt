package com.ajinkya.formula1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.common.Constant

@Entity(tableName = Constant.DRIVER_TABLE)
data class Driver(
    @PrimaryKey
    var position: String = "",
    var points: String = "",
    var constructorName: String = "",
    var driverName: String = "",
)
