package com.ajinkya.formula1.feature.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajinkya.formula1.data.repository.DriverRepository
import com.ajinkya.formula1.domain.use_case.GetDriversUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HiltViewModel -
 * MutableStateFlow -
 */
@HiltViewModel
class DriverViewModel @Inject constructor(
    private val driverRepository: DriverRepository,
    private val getDriversUseCase: GetDriversUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DriverState())
    val state = _state.asStateFlow()

    init {
        getConstructorStandings()
        loadConstructorStandings()
    }

    private fun getConstructorStandings() {
        viewModelScope.launch {
            getDriversUseCase()
                .flowOn(Dispatchers.IO)
                .collect { driverList ->
                    _state.update {
                        it.copy(drivers = driverList)
                    }
                }
        }
    }

    private fun loadConstructorStandings() {
        viewModelScope.launch {
            driverRepository.loadDrivers()
                .catch { }
                .collect()
        }
    }
}