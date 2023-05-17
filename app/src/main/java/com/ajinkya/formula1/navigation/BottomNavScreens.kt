package com.ajinkya.formula1.navigation

import com.ajinkya.formula1.R

sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: Int,
    val tag : String
) {
    object Schedule : BottomNavScreens(
        route = "schedule",
        title = "Schedule",
        icon = R.drawable.flag_checkered,
        tag = "scheduleTab",
    )

    object Driver : BottomNavScreens(
        route = "driver",
        title = "Driver",
        icon = R.drawable.racing_helmet,
        tag = "driverTab",
    )

    object Constructor : BottomNavScreens(
        route = "constructor",
        title = "Constructor",
        icon = R.drawable.progress_wrench,
        tag = "constructorTab",
    )
}
