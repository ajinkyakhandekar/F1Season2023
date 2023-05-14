package com.ajinkya.formula1.feature.constructor

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ajinkya.formula1.core.data.repository.ConstructorRepository
import com.ajinkya.formula1.core.model.Constructor
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ConstructorViewModelTest {

    @get:Rule
    var instantExecutable = InstantTaskExecutorRule()

    private var constructorRepository = mock<ConstructorRepository>()
    private lateinit var constructorViewModel: ConstructorViewModel

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        constructorViewModel = ConstructorViewModel(
            constructorRepository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchConstructorList() = runTest {
        whenever(constructorRepository.getConstructors())
            .thenReturn(flowOf(givenConstructors))

        constructorViewModel.getConstructorStandings()
        dispatcher.scheduler.advanceUntilIdle()

        Truth.assertThat(constructorViewModel.state.value.constructors)
            .hasSize(2)
        Truth.assertThat(constructorViewModel.state.value.constructors[1].points)
            .isEqualTo("46")
    }
}

val givenConstructors = listOf(
    Constructor(
        position = "1",
        points = "23",
        constructorName = "Ferrari",
    ),
    Constructor(
        position = "2",
        points = "46",
        constructorName = "Mercedes",
    )
)