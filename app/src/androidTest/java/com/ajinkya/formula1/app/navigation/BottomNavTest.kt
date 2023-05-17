package com.ajinkya.formula1.app.navigation

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.ajinkya.formula1.navigation.BottomNavScreens
import com.ajinkya.formula1.ui.ComposeActivity
import com.ajinkya.formula1.ui.screen.BottomNavBar
import com.ajinkya.formula1.ui.screen.F1Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class BottomNavTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComposeActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            F1Screen(navController = navController)
        }
    }

    @Test
    fun bottomNav_startDestination_isDisplayed() {
        composeTestRule.onNodeWithTag(testTag = "ScheduleScreen")
            .assertIsDisplayed()
    }

    @Test
    fun bottomNavHost_clickDriverTab_navigateToDriverTab() {
        composeTestRule.onNodeWithTag(BottomNavScreens.Driver.tag)
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, BottomNavScreens.Driver.route)

        composeTestRule.onNodeWithTag(testTag = "DriverScreen")
            .assertIsDisplayed()
    }

    @Test
    fun bottomNavHost_clickConstructorTab_navigateToConstructorTab() {
        composeTestRule.onNodeWithTag(BottomNavScreens.Constructor.tag)
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, BottomNavScreens.Constructor.route)

        composeTestRule.onNodeWithTag(testTag = "ConstructorScreen")
            .assertIsDisplayed()
    }
}
