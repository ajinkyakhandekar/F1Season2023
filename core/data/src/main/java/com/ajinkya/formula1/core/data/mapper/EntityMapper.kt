package com.ajinkya.formula1.core.data.mapper

import com.ajinkya.formula1.core.database.entity.ConstructorEntity
import com.ajinkya.formula1.core.database.entity.DriverEntity
import com.ajinkya.formula1.core.database.entity.ScheduleEntity
import com.ajinkya.formula1.core.model.Constructor
import com.ajinkya.formula1.core.model.Driver
import com.ajinkya.formula1.core.model.Schedule

fun ScheduleEntity.toModel(): Schedule {
    return Schedule(
        round = round,
        raceName = raceName,
        country = country,
        date = date,
        time = time,
    )
}

fun DriverEntity.toModel(): Driver {
    return Driver(
        position = position,
        points = points,
        constructorName = constructorName,
        driverName = driverName,
    )
}

fun ConstructorEntity.toModel(): Constructor {
    return Constructor(
        position = position,
        points = points,
        constructorName = constructorName,
    )
}
