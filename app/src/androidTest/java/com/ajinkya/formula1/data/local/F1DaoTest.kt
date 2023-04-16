package com.ajinkya.formula1.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.ajinkya.formula1.data.local.database.F1Dao
import com.ajinkya.formula1.data.local.database.F1Database
import com.ajinkya.formula1.data.local.entity.Driver
import com.ajinkya.formula1.data.local.entity.Schedule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * RunWith - Declare the testing library with we want to Test
 * @get:Rule -
 * instantTaskExecutorRule -
 *
 * Before - Call this function before every Test
 * After - Call this function after every Test
 *
 * inMemoryDatabaseBuilder - Hold the database in RAM, do not create an actual database
 * allowMainThreadQueries - Disables Main thread query check
 *
 * runTest - Executes testBody as a test in a new coroutine and skip any delays
 * Turbine - Library to test Flow
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class F1DaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var f1Database: F1Database
    private lateinit var f1Dao: F1Dao

    @Before
    fun setup() {
        f1Database = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = F1Database::class.java
        ).allowMainThreadQueries().build()

        f1Dao = f1Database.f1Dao()
    }

    @After
    fun teardown() {
        f1Database.close()
    }

    @Test
    fun insertSchedule() = runTest {
        val scheduleList = listOf(Schedule(round = "1"), Schedule(round = "2"))
        f1Dao.insertSchedule(scheduleList)

        f1Dao.getSchedule().test {
            assertThat(awaitItem().size).isEqualTo(2)
        }
    }

    @Test
    fun insertDriver() = runTest {
        val driverList = listOf(Driver(position = "1"), Driver(position = "2"))
        f1Dao.insertDrivers(driverList)

        f1Dao.getSchedule().test {
            assertThat(awaitItem().size).isEqualTo(2)
        }
    }
}
