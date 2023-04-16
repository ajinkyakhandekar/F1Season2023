package com.ajinkya.formula1.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.ajinkya.formula1.R

sealed class BottomNavScreens(
    val route: String,
    val title: String,
    val icon: Int
){
    object Schedule : BottomNavScreens(
        route = "schedule",
        title = "Schedule",
        icon = R.drawable.flag_checkered
    )

    object Driver : BottomNavScreens(
        route = "driver",
        title = "Driver",
        icon =  R.drawable.racing_helmet
    )

    object Constructor : BottomNavScreens(
        route = "constructor",
        title = "Constructor",
        icon =  R.drawable.progress_wrench
    )
}
