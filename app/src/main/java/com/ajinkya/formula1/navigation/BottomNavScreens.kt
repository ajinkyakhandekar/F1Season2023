package com.ajinkya.formula1.navigation

import com.ajinkya.formula1.R

sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Schedule : BottomNavScreens(
        route = "schedule",
        title = "Schedule",
        icon = R.drawable.flag_checkered
    )

    object Driver : BottomNavScreens(
        route = "driver",
        title = "Driver",
        icon = R.drawable.racing_helmet
    )

    object Constructor : BottomNavScreens(
        route = "constructor",
        title = "Constructor",
        icon = R.drawable.progress_wrench
    )
}
