package com.ajinkya.formula1.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ajinkya.formula1.R
import com.ajinkya.formula1.navigation.BottomNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun F1Screen(
    navController: NavHostController = rememberNavController()
) {
    val f1ViewModel = hiltViewModel<F1ViewModel>()

    LaunchedEffect(key1 = Unit) {
        f1ViewModel.loadSchedule()
        f1ViewModel.loadDriver()
        f1ViewModel.loadConstructor()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
            )
        },
        content = {
            BottomNavHost(
                navController = navController,
                modifier = Modifier.padding(it)
            )
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    )
}