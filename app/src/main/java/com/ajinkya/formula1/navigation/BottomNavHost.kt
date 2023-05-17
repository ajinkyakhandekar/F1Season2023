package com.ajinkya.formula1.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ajinkya.formula1.feature.constructor.ConstructorScreen
import com.ajinkya.formula1.feature.constructor.ConstructorViewModel
import com.ajinkya.formula1.feature.driver.DriverScreen
import com.ajinkya.formula1.feature.driver.DriverViewModel
import com.ajinkya.formula1.feature.schedule.ScheduleScreen
import com.ajinkya.formula1.feature.schedule.ScheduleViewModel

@Composable
fun BottomNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
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
            val scheduleState = scheduleViewModel.state.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = Unit) {
                scheduleViewModel.getSchedule()
            }
            ScheduleScreen(
                modifier = Modifier.padding(12.dp)
                    .testTag("ScheduleScreen"),
                schedule = scheduleState.value.schedule
            )
        }

        composable(
            route = BottomNavScreens.Driver.route
        ) {
            val driverViewModel = hiltViewModel<DriverViewModel>()
            val driverState = driverViewModel.state.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = Unit) {
                driverViewModel.getDriverStandings()
            }
            DriverScreen(
                modifier = Modifier.padding(12.dp)
                    .testTag("DriverScreen"),
                drivers = driverState.value.drivers
            )
        }

        composable(
            route = BottomNavScreens.Constructor.route
        ) {
            val constructorViewModel = hiltViewModel<ConstructorViewModel>()
            val constructorState = constructorViewModel.state.collectAsStateWithLifecycle()
            LaunchedEffect(key1 = Unit) {
                constructorViewModel.getConstructorStandings()
            }
            ConstructorScreen(
                modifier = Modifier.padding(12.dp)
                    .testTag("ConstructorScreen"),
                constructors = constructorState.value.constructors
            )
        }
    }
}
