package com.ajinkya.formula1.data.remote.dto

data class StandingsDto(
    val MRData: MRDataStandings
)

data class MRDataStandings(
    val StandingsTable: StandingsTable,
    val limit: String,
    val offset: String,
    val series: String,
    val total: String,
    val url: String,
    val xmlns: String
)

data class StandingsTable(
    val StandingsLists: List<StandingsLists>,
    val season: String
)

data class StandingsLists(
    val DriverStandings: List<DriverStanding>,
    val ConstructorStandings: List<ConstructorStanding>,
    val round: String,
    val season: String
)

data class DriverStanding(
    val Constructors: List<ConstructorDto>,
    val Driver: DriverDto,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)

data class ConstructorStanding(
    val Constructor: ConstructorDto,
    val points: String,
    val position: String,
    val positionText: String,
    val wins: String
)

data class ConstructorDto(
    val constructorId: String,
    val name: String,
    val nationality: String,
    val url: String
)

data class DriverDto(
    val code: String,
    val dateOfBirth: String,
    val driverId: String,
    val familyName: String,
    val givenName: String,
    val nationality: String,
    val permanentNumber: String,
    val url: String
)
