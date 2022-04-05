package com.ajinkya.formula1.data.remote.dto


data class ScheduleDto(
    var MRData: MRData = MRData()
)

data class MRData(
    var RaceTable: RaceTable = RaceTable(),
    var limit: String = "",
    var offset: String = "",
    var series: String = "",
    var total: String = "",
    var url: String = "",
    var xmlns: String = ""
)

data class RaceTable(
    var Races: MutableList<Race> = mutableListOf(),
    var season: String = ""
)

data class Race(
    var Circuit: Circuit = Circuit(),
    var FirstPractice: FirstPractice = FirstPractice(),
    var Qualifying: Qualifying = Qualifying(),
    var SecondPractice: SecondPractice = SecondPractice(),
    var Sprint: Sprint = Sprint(),
    var ThirdPractice: ThirdPractice = ThirdPractice(),
    var date: String = "",
    var raceName: String = "",
    var round: String = "",
    var season: String = "",
    var time: String = "",
    var url: String = ""
)

data class Circuit(
    var Location: Location = Location(),
    var circuitId: String = "",
    var circuitName: String = "",
    var url: String = ""
)

data class FirstPractice(
    var date: String = "",
    var time: String = ""
)

data class Qualifying(
    var date: String = "",
    var time: String = ""
)

data class SecondPractice(
    var date: String = "",
    var time: String = ""
)

data class Sprint(
    var date: String = "",
    var time: String = ""
)

data class ThirdPractice(
    var date: String = "",
    var time: String = ""
)

data class Location(
    var country: String = "",
    var lat: String = "",
    var locality: String = "",
    var long: String = ""
)