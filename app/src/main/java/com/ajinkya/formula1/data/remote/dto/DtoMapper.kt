package com.ajinkya.formula1.data.remote.dto

import com.ajinkya.formula1.data.local.entity.ConstructorEntity
import com.ajinkya.formula1.data.local.entity.DriverEntity
import com.ajinkya.formula1.data.local.entity.ScheduleEntity


fun ScheduleDto.mapSchedule(): List<ScheduleEntity> {
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


fun StandingsDto.mapDriver(): List<DriverEntity> {
    if (MRData.StandingsTable.StandingsLists.isEmpty()) return emptyList()

    return MRData.StandingsTable.StandingsLists[0].DriverStandings.map {
        DriverEntity().apply {
            position = it.position
            points = it.points
            driverName = "${it.Driver.givenName} ${it.Driver.familyName}"
            constructorName = if (it.Constructors.isEmpty()) "" else it.Constructors[0].name
        }
    }
}


fun StandingsDto.mapConstructor(): List<ConstructorEntity> {
    if (MRData.StandingsTable.StandingsLists.isEmpty()) return emptyList()

    return MRData.StandingsTable.StandingsLists[0].ConstructorStandings.map {
        ConstructorEntity(
            position = it.position,
            points = it.points,
            constructorName = it.Constructor.name
        )
    }
}