package com.ajinkya.formula1.core.model

data class Schedule(
    val round: String,
    val raceName: String,
    val country: String,
    var date: String,
    var time: String,
)
