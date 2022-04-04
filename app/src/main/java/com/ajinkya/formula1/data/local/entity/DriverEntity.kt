package com.ajinkya.formula1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ajinkya.formula1.common.Constant
import com.ajinkya.formula1.domain.model.Driver

@Entity(tableName = Constant.DRIVER_TABLE)
data class DriverEntity(
    @PrimaryKey
    var position: String = "",
    var points: String = "",
    var constructorName: String = "",
    var driverName: String = "",
) {
    fun mapDriver(): Driver {
        return Driver(
            position = position,
            points = points,
            constructorName = constructorName,
            driverName = driverName
        )
    }
}
