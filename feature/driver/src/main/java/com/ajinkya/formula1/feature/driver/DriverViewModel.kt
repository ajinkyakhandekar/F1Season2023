package com.ajinkya.formula1.feature.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.core.data.repository.DriverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(
    private val driverRepository: DriverRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DriverState())
    val state = _state.asStateFlow()

    fun getDriverStandings() {
        viewModelScope.launch {
            driverRepository.getDrivers()
                .collect { driverList ->
                    _state.update {
                        it.copy(drivers = driverList)
                    }
                }
        }
    }
}