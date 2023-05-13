package com.ajinkya.formula1.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ajinkya.formula1.core.designsystem.theme.DarkOrange
import com.ajinkya.formula1.core.designsystem.theme.GrayDark
import com.ajinkya.formula1.navigation.BottomNavScreens

@Composable
fun BottomNavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val screens = listOf(
        BottomNavScreens.Schedule,
        BottomNavScreens.Driver,
        BottomNavScreens.Constructor,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    ) {
        screens.forEach { screen ->

            val isSelected = currentDestination?.hierarchy?.any {
                it.route == screen.route
            } == true

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkOrange,
                    selectedTextColor = Color.Black,
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = GrayDark,
                    indicatorColor = Color.White,
                ),
                selected = isSelected,
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    val colorFilter = if (isSelected) ColorFilter.tint(DarkOrange)
                    else ColorFilter.tint(Color.Black)
                    Image(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "Navigation Icon",
                        colorFilter = colorFilter
                    )
                },
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}
