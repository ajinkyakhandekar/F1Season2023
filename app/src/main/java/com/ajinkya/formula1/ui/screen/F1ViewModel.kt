package com.ajinkya.formula1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.core.data.repository.ConstructorRepository
import com.ajinkya.formula1.core.data.repository.DriverRepository
import com.ajinkya.formula1.core.data.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class F1ViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    private val driverRepository: DriverRepository,
    private val constructorRepository: ConstructorRepository,
) : ViewModel() {

    fun loadSchedule() {
        viewModelScope.launch {
            scheduleRepository.loadSchedule()
                .collect{}
        }
    }

    fun loadDriver() {
        viewModelScope.launch {
            driverRepository.loadDrivers()
                .collect{}
        }
    }

    fun loadConstructor() {
        viewModelScope.launch {
            constructorRepository.loadConstructors()
                .collect{}
        }
    }
}