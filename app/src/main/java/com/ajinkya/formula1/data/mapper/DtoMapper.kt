package com.ajinkya.formula1.data.mapper

import com.ajinkya.formula1.data.local.entity.Constructor
import com.ajinkya.formula1.data.local.entity.Driver
import com.ajinkya.formula1.data.local.entity.Schedule
import com.ajinkya.formula1.data.remote.dto.ScheduleDto
import com.ajinkya.formula1.data.remote.dto.StandingsDto


fun ScheduleDto.mapSchedule(): List<Schedule> {
    return MRData.RaceTable.Races.map {
        Schedule(
            round = it.round,
            raceName = it.raceName,
            country = it.Circuit.Location.country,
            date = it.date,
            time = it.time
        )
    }
}


fun StandingsDto.mapDriver(): List<Driver> {
    if (MRData.StandingsTable.StandingsLists.isEmpty()) return emptyList()

    return MRData.StandingsTable.StandingsLists[0].DriverStandings.map {
        Driver().apply {
            position = it.position
            points = it.points
            driverName = "${it.Driver.givenName} ${it.Driver.familyName}"
            constructorName = if (it.Constructors.isEmpty()) "" else it.Constructors[0].name
        }
    }
}


fun StandingsDto.mapConstructor(): List<Constructor> {
    if (MRData.StandingsTable.StandingsLists.isEmpty()) return emptyList()

    return MRData.StandingsTable.StandingsLists[0].ConstructorStandings.map {
        Constructor(
            position = it.position,
            points = it.points,
            constructorName = it.Constructor.name
        )
    }
}