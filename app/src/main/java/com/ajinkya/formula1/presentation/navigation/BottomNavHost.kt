package com.ajinkya.formula1.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ajinkya.formula1.presentation.constructor.ConstructorScreen
import com.ajinkya.formula1.presentation.constructor.ConstructorViewModel
import com.ajinkya.formula1.presentation.driver.DriverScreen
import com.ajinkya.formula1.presentation.driver.DriverViewModel
import com.ajinkya.formula1.presentation.schedule.ScheduleScreen
import com.ajinkya.formula1.presentation.schedule.ScheduleViewModel

@Composable
fun BottomNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = BottomNavScreens.Schedule.route
    ) {

        composable(
            route = BottomNavScreens.Schedule.route
        ) {
            val scheduleViewModel = hiltViewModel<ScheduleViewModel>()
            val scheduleState = scheduleViewModel.state.collectAsState().value
            ScheduleScreen(
                modifier = Modifier.padding(12.dp),
                schedule = scheduleState.schedule
            )
        }

        composable(
            route = BottomNavScreens.Driver.route
        ) {
            val driverViewModel = hiltViewModel<DriverViewModel>()
            val driverState = driverViewModel.state.collectAsState().value
            DriverScreen(
                modifier = Modifier.padding(12.dp),
                drivers = driverState.drivers
            )
        }

        composable(
            route = BottomNavScreens.Constructor.route
        ) {
            val constructorViewModel = hiltViewModel<ConstructorViewModel>()
            val constructorState = constructorViewModel.state.collectAsState().value
            ConstructorScreen(
                modifier = Modifier.padding(12.dp),
                constructors = constructorState.constructors
            )
        }
    }
}
