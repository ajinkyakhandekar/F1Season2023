package com.ajinkya.formula1.core.data.mapper

import com.ajinkya.formula1.core.database.entity.ConstructorEntity
import com.ajinkya.formula1.core.database.entity.DriverEntity
import com.ajinkya.formula1.core.database.entity.ScheduleEntity
import com.ajinkya.formula1.core.network.dto.ScheduleDto
import com.ajinkya.formula1.core.network.dto.StandingsDto

fun ScheduleDto.toScheduleEntity(): List<ScheduleEntity> {
    return MRData.RaceTable.Races.map {
        ScheduleEntity(
            round = it.round,
            raceName = it.raceName,
            country = it.Circuit.Location.country,
            date = it.date,
            time = it.time
        )
    }
}


fun StandingsDto.toDriverEntity(): List<DriverEntity> {
    if (MRData.StandingsTable.StandingsLists.isEmpty()) return emptyList()

    return MRData.StandingsTable.StandingsLists[0].DriverStandings.map {
        DriverEntity(
            position = it.position,
            points = it.points,
            driverName = "${it.Driver.givenName} ${it.Driver.familyName}",
            constructorName = if (it.Constructors.isEmpty()) "" else it.Constructors[0].name
        )
    }
}


fun StandingsDto.toConstructorEntity(): List<ConstructorEntity> {
    if (MRData.StandingsTable.StandingsLists.isEmpty()) return emptyList()

    return MRData.StandingsTable.StandingsLists[0].ConstructorStandings.map {
        ConstructorEntity(
            position = it.position,
            points = it.points,
            constructorName = it.Constructor.name
        )
    }
}