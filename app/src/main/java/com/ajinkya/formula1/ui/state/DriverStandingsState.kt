package com.ajinkya.formula1.ui.state

import com.ajinkya.formula1.domain.model.Driver

data class DriverStandingsState(
    var isLoading: Boolean = false,
    var driverList: List<Driver> = emptyList(),
    var errorMessage: String = ""
)