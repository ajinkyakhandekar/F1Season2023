package com.ajinkya.formula1.presentation.driver

import com.ajinkya.formula1.data.local.entity.Driver

data class DriverState(
    var drivers: List<Driver> = emptyList()
)
