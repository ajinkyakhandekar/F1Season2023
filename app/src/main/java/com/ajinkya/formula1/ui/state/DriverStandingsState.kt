package com.ajinkya.formula1.ui.state

import com.ajinkya.formula1.domain.model.Driver

sealed class DriverStandingsState {
    data class Loading(var isLoading: Boolean): DriverStandingsState()
    data class Success(var drivers: List<Driver>): DriverStandingsState()
    data class Error(var error: String): DriverStandingsState()
}
