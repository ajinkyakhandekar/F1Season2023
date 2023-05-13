package com.ajinkya.formula1.core.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.ajinkya.formula1.core.database.entity.DriverEntity
import com.ajinkya.formula1.core.database.entity.ScheduleEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
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
        f1Dao.insertSchedule(givenSchedule)

        f1Dao.getSchedule().test {
            assertThat(awaitItem().size).isEqualTo(2)
        }
    }

    @Test
    fun insertDriver() = runTest {
        f1Dao.insertDrivers(givenDrivers)

        f1Dao.getDrivers().test {
            assertThat(awaitItem().size).isEqualTo(2)
        }
    }
}

val givenSchedule = listOf(
    ScheduleEntity(
        round = "1",
        raceName = "Silverstone",
        country = "UK",
        date = "2023-02-12",
        time = "18:45:00+0000",
    ),
    ScheduleEntity(
        round = "2",
        raceName = "Imola",
        country = "Italy",
        date = "2023-07-22",
        time = "11:30:00+0000",
    )
)


val givenDrivers = listOf(
    DriverEntity(
        position = "1",
        points = "23",
        constructorName = "Ferrari",
        driverName = "Carlos"
    ),
    DriverEntity(
        position = "2",
        points = "46",
        constructorName = "Mercedes",
        driverName = "Max"
    )
)
