package com.ajinkya.formula1.data.entity

data class StandingsResponse(
    var MRData: MRDataStandings = MRDataStandings()
)

data class MRDataStandings(
    var StandingsTable: StandingsTable = StandingsTable(),
    var limit: String = "",
    var offset: String = "",
    var series: String = "",
    var total: String = "",
    var url: String = "",
    var xmlns: String = ""
)

data class StandingsTable(
    var StandingsLists: MutableList<StandingsLists> = mutableListOf(),
    var season: String = ""
)

data class StandingsLists(
    var DriverStandings: MutableList<DriverStanding> = mutableListOf(),
    var ConstructorStandings: MutableList<ConstructorStanding> = mutableListOf(),
    var round: String = "",
    var season: String = ""
)

data class DriverStanding(
    var Constructors: MutableList<Constructor> = mutableListOf(),
    var Driver: Driver = Driver(),
    var points: String = "",
    var position: String = "",
    var positionText: String = "",
    var wins: String = ""
)

data class ConstructorStanding(
    var Constructor: Constructor = Constructor(),
    var points: String = "",
    var position: String = "",
    var positionText: String = "",
    var wins: String = ""
)

data class Constructor(
    var constructorId: String = "",
    var name: String = "",
    var nationality: String = "",
    var url: String = ""
)

data class Driver(
    var code: String = "",
    var dateOfBirth: String = "",
    var driverId: String = "",
    var familyName: String = "",
    var givenName: String = "",
    var nationality: String = "",
    var permanentNumber: String = "",
    var url: String = ""
)